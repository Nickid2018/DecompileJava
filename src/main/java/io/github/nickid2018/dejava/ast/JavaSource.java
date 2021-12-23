package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.ast.classes.ClassDecl;

public class JavaSource implements INode {

    private ClassDecl clazz;
    private String packageName;

    public ClassDecl getClassDecl() {
        return clazz;
    }

    public JavaSource setClassDecl(ClassDecl clazz) {
        this.clazz = clazz;
        return this;
    }

    public JavaSource setPackage(String name) {
        this.packageName = name;
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .statement(ConstantNames.PACKAGE, packageName)
                .line().line()
                .append(clazz.toSource(fc))
                .toSource();
    }

}
