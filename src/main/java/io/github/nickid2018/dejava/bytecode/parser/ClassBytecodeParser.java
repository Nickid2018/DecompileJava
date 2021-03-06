/*
 * Copyright 2021 Nickid2018
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.nickid2018.dejava.bytecode.parser;

import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.api.ClassFileProvider;
import io.github.nickid2018.dejava.api.ClassType;
import io.github.nickid2018.dejava.api.FieldType;
import io.github.nickid2018.dejava.bytecode.classformat.AbstractClassFormat;
import io.github.nickid2018.dejava.bytecode.classformat.ModuleFormat;
import io.github.nickid2018.dejava.bytecode.classformat.PlainClassFormat;
import io.github.nickid2018.dejava.bytecode.classformat.SignatureInfos;
import io.github.nickid2018.dejava.bytecode.fieldformat.AbstractFieldFormat;
import io.github.nickid2018.dejava.bytecode.fieldformat.PlainFieldFormat;
import org.objectweb.asm.*;

import java.io.IOException;

public class ClassBytecodeParser extends ClassVisitor implements Opcodes {

    private final String fileName;
    private final ClassFileProvider fileProvider;
    private AbstractClassFormat classFormat;
    private ModuleFormat moduleFormat;
    private boolean isSynthetic;
    private boolean isModule;

    public ClassBytecodeParser(String fileName, ClassFileProvider provider) {
        super(ASM9);
        this.fileName = fileName;
        fileProvider = provider;
    }

    public AbstractClassFormat analyze() throws IOException {
        new ClassReader(fileProvider.getClassFile(fileName)).accept(this, 0);
        return getClassFormat();
    }

    public String getFileName() {
        return fileName;
    }

    public AbstractClassFormat getClassFormat() {
        return classFormat;
    }

    public ModuleFormat getModuleFormat() {
        return moduleFormat;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        ClassType type = ClassType.getTypeWithAccessFlag(access);
        if (isSynthetic = (type == ClassType.SYNTHETIC))
            type = ClassType.getTypeWithAccessFlag(access ^ ACC_SYNTHETIC);
        classFormat = switch (type) {
            case CLASS -> new PlainClassFormat(name, access, superName, interfaces, fileProvider);
            case MODULE -> {
                isModule = true;
                yield null;
            }
            default -> throw new DecompileException("Unknown type! But it is impossible!");
        };
        if (!isModule && signature != null && !signature.isEmpty())
            classFormat.setSignature(new SignatureInfos(signature));
    }

    @Override
    public void visitSource(String source, String debug) {
        // Ignore
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        // Ignore: Unknown attribute
    }

    @Override
    public void visitEnd() {
        // Ignore
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldType type = FieldType.getTypeWithAccessFlag(access);
        boolean isSynthetic;
        if (isSynthetic = (type == FieldType.SYNTHETIC))
            type = FieldType.getTypeWithAccessFlag(access ^ ACC_SYNTHETIC);
        AbstractFieldFormat format = switch (type) {
            case PLAIN -> new PlainFieldFormat(classFormat, name, descriptor, access, value);
            default -> throw new DecompileException("Unknown type! But it is impossible!");
        };
        format.setSynthetic(isSynthetic || this.isSynthetic);
        classFormat.addField(name, format);
        return new FieldBytecodeParser(format);
    }

    @Override
    public ModuleVisitor visitModule(String name, int access, String version) {
        ModuleFormat format = new ModuleFormat(name, access, fileProvider);
        return new ModuleBytecodeParser(format);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return super.visitAnnotation(descriptor, visible);
    }
}
