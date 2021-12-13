package io.github.nickid2018.dejava.astparse;

import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.api.ClassFileProvider;
import io.github.nickid2018.dejava.api.ClassType;
import io.github.nickid2018.dejava.classformat.AbstractClassFormat;
import io.github.nickid2018.dejava.classformat.PlainClassFormat;
import io.github.nickid2018.dejava.classformat.SignatureInfos;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class ClassASTParser extends ClassVisitor implements Opcodes {

    private AbstractClassFormat classFormat;
    private final String fileName;
    private final ClassFileProvider fileProvider;
    private boolean isSynthetic;

    public ClassASTParser(int api, String fileName, ClassFileProvider provider) {
        super(ASM9);
        this.fileName = fileName;
        fileProvider = provider;
    }

    public String getFileName() {
        return fileName;
    }

    public AbstractClassFormat getClassFormat() {
        return classFormat;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        ClassType type = ClassType.getTypeWithAccessFlag(access);
        if (isSynthetic = (type == ClassType.SYNTHETIC))
            type = ClassType.getTypeWithAccessFlag(access ^ ACC_SYNTHETIC);
        classFormat = switch (type) {
            case CLASS -> new PlainClassFormat(name, access, superName, interfaces, fileProvider);
            default -> throw new DecompileException("Unknown type! But it is impossible!");
        };
        if (signature != null && !signature.isEmpty())
            classFormat.setSignature(new SignatureInfos(signature));
    }

    @Override
    public void visitSource(String source, String debug) {
        // Ignore
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        // Ignore
    }

    @Override
    public void visitEnd() {
        // Ignore
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {

        return super.visitField(access, name, descriptor, signature, value);
    }
}
