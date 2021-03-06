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

package io.github.nickid2018.dejava.api.visitor;

import io.github.nickid2018.dejava.DecompileSettings;
import io.github.nickid2018.dejava.api.*;
import io.github.nickid2018.dejava.util.Checkers;
import io.github.nickid2018.dejava.util.ModifierUtil;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor for classes. The order is:
 * <code>(visitPackage)(visitImports)visitClass(visitSignature)(visitField)*(visitMethod)*visitEnd</code>
 */
public abstract class ClassEntryVisitor {

    // Access flags using in class declaration
    public static final int ACC_PUBLIC = Opcodes.ACC_PUBLIC;
    public static final int ACC_FINAL = Opcodes.ACC_FINAL;
    public static final int ACC_SUPER = Opcodes.ACC_SUPER; // No actual use for decompiling
    public static final int ACC_INTERFACE = Opcodes.ACC_INTERFACE;
    public static final int ACC_ABSTRACT = Opcodes.ACC_ABSTRACT;
    public static final int ACC_SYNTHETIC = Opcodes.ACC_SYNTHETIC;
    public static final int ACC_ANNOTATION = Opcodes.ACC_ANNOTATION;
    public static final int ACC_ENUM = Opcodes.ACC_ENUM;
    public static final int ACC_MODULE = Opcodes.ACC_MODULE;
    public static final int ACC_RECORD = Opcodes.ACC_RECORD;
    public static final int ACC_DEPRECATED = Opcodes.ACC_DEPRECATED;

    public static List<IModifier> parseClassEntryAccessFlag(int accessFlag) {
        List<IModifier> modifiers = new ArrayList<>();
        boolean isFinal, isAbstract;
        if (ModifierUtil.checkFlag(accessFlag, ACC_PUBLIC))
            modifiers.add(Modifiers.PUBLIC);
        if (isFinal = ModifierUtil.checkFlag(accessFlag, ACC_FINAL))
            modifiers.add(Modifiers.FINAL);
        if (isAbstract = ModifierUtil.checkFlag(accessFlag, ACC_ABSTRACT))
            modifiers.add(Modifiers.ABSTRACT);
        // --- Check modifiers
        Checkers.errorIfTrue(isFinal && isAbstract, "A class can't be both final and abstract");
        Checkers.errorIfTrue(ModifierUtil.checkFlag(accessFlag, ACC_INTERFACE) && !isAbstract,
                "An interface should be abstract");
        Checkers.errorIfTrue(ModifierUtil.checkFlag(accessFlag, ACC_ENUM) && !isFinal,
                "An enum should be final");
        Checkers.errorIfTrue(ModifierUtil.checkFlag(accessFlag, ACC_RECORD) && !isFinal,
                "A record should be final");
        Checkers.errorIfTrue(ModifierUtil.checkFlag(accessFlag, ACC_INTERFACE) &&
                        !ModifierUtil.checkFlag(accessFlag, ACC_ANNOTATION),
                "An annotation type should be an interface");
        return modifiers;
    }

    /**
     * Visit the package name.
     *
     * @param packageName the package class locate
     */
    public void visitPackage(String packageName) {
    }

    /**
     * Visit import operations.
     *
     * @return a visitor to visit imports
     */
    public ImportEntryVisitor visitImports() {
        return null;
    }

    /**
     * Visit class declaration.
     *
     * @param accessFlag access flag of the class, may be these as follows:
     *                   <ul>
     *                      <li>{@link #ACC_PUBLIC} - The class is public.</li>
     *                      <li>{@link #ACC_FINAL} - The class is final.</li>
     *                      <li>{@link #ACC_SUPER} - The superclass methods specially when invoked by the {@code invokespecial} instruction.</li>
     *                      <li>{@link #ACC_INTERFACE} - The class is an interface (with abstract).</li>
     *                      <li>{@link #ACC_ABSTRACT} - The class is abstract.</li>
     *                      <li>{@link #ACC_SYNTHETIC} - The class is synthetic.
     *                             If {@link DecompileSettings#noSynthetic} is true, the method can't receive this. (such as: switch table inner class)</li>
     *                      <li>{@link #ACC_ANNOTATION} - The class is an annotation type.</li>
     *                      <li>{@link #ACC_ENUM} - The class is an enum.</li>
     *                      <li>{@link #ACC_MODULE} - The class is a module.</li>
     *                      <li>{@link #ACC_RECORD} - The class is a record.</li>
     *                      <li>{@link #ACC_DEPRECATED} - The class is deprecated.</li>
     *                   </ul>
     * @param name       name of the class
     * @param superClass super class of the class, maybe null if the class doesn't need a superclass - such as enums
     * @param interfaces interfaces of the class, maybe null if the class has no interfaces
     */
    public void visitClass(int accessFlag, String name, String superClass, String... interfaces) {
    }

    /**
     * Visit the signature of the file.
     *
     * @return a visitor to read signature
     */
    public SignatureVisitor visitSignature() {
        return null;
    }

    /**
     * Visit field declaration. The field may be a plain field, an enum field or a record component.
     *
     * @param fieldType type of the field
     * @return a visitor to visit field
     */
    public FieldEntryVisitor visitFieldEntry(FieldType fieldType) {
        return null;
    }

    /**
     * Visit method declaration.
     *
     * @param fieldType type of the method
     * @return a visitor to visit method
     */
    public MethodEntryVisitor visitMethodEntry(MethodType fieldType) {
        return null;
    }

    /**
     * Visit an inner class.
     * @return a class visitor to visit the inner class
     */
    public ClassEntryVisitor visitInnerClassEntry(ClassType type){
        return null;
    }

    /**
     * Visit when the operations are over.
     */
    public void visitEnd() {
    }
}
