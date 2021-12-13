package io.github.nickid2018.dejava.classformat;

import io.github.nickid2018.dejava.api.visitor.SignatureEntryVisitor;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;

import static org.objectweb.asm.Opcodes.ASM9;

public record SignatureInfos(String signature) {

    public static final int ACCURATE_CLASS = 0;
    public static final int EXTEND_CLASS = 1;
    public static final int SUPER_CLASS = 2;

    public void fireVisit(SignatureEntryVisitor visitor) {
        new SignatureReader(signature).accept(new Visitor(visitor));
    }

    private static class Visitor extends SignatureVisitor {

        private final SignatureEntryVisitor visitor;

        Visitor(SignatureEntryVisitor visitor){
            super(ASM9);
            this.visitor = visitor;
        }

        @Override
        public void visitFormalTypeParameter(String name) {
            super.visitFormalTypeParameter(name);
        }

        @Override
        public SignatureVisitor visitClassBound() {
            return super.visitClassBound();
        }

        @Override
        public SignatureVisitor visitInterfaceBound() {
            return super.visitInterfaceBound();
        }

        @Override
        public SignatureVisitor visitSuperclass() {
            return super.visitSuperclass();
        }

        @Override
        public SignatureVisitor visitInterface() {
            return super.visitInterface();
        }

        @Override
        public SignatureVisitor visitParameterType() {
            return super.visitParameterType();
        }

        @Override
        public SignatureVisitor visitReturnType() {
            return super.visitReturnType();
        }

        @Override
        public SignatureVisitor visitExceptionType() {
            return super.visitExceptionType();
        }

        @Override
        public void visitBaseType(char descriptor) {
            super.visitBaseType(descriptor);
        }

        @Override
        public void visitTypeVariable(String name) {
            super.visitTypeVariable(name);
        }

        @Override
        public SignatureVisitor visitArrayType() {
            return super.visitArrayType();
        }

        @Override
        public void visitClassType(String name) {
            super.visitClassType(name);
        }

        @Override
        public void visitInnerClassType(String name) {
            super.visitInnerClassType(name);
        }

        @Override
        public void visitTypeArgument() {
            super.visitTypeArgument();
        }

        @Override
        public SignatureVisitor visitTypeArgument(char wildcard) {
            return super.visitTypeArgument(wildcard);
        }

        @Override
        public void visitEnd() {
            super.visitEnd();
        }
    }
}
