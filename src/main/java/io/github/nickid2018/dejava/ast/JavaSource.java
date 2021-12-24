package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.ast.classes.ClassDecl;

/**
 * Represent a Java compilation unit (source file).
 * 
 * It is the root of the AST and the final target of the output Java file.
 * 
 * <pre>
 * CompilationUnit:
 *  PackageDeclaration
 *  ImportDeclaration*
 *  TopLevelClassOrInterfaceDeclaration
 * </pre>
 * 
 * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-7.html#jls-7.3">
 *  Compilation Units</a>
 */
public class JavaSource implements INode {
    // TODO change clazz to typeDeclList
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
