package io.github.nickid2018.dejava.api.visitor;

import io.github.nickid2018.dejava.api.ClassType;

public interface ClassEntryVisitor {

    void visitPackage(String packageName);

    ImportEntryVisitor visitImports();

    SignatureEntryVisitor visitSignature();

    void visitClass(int accessFlag, ClassType type, String name, String superClass, String[] interfaces);

    FieldEntryVisitor visitFieldEntry(int accessFlag, String type, String name);
}
