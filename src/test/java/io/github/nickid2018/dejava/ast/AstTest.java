package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.ast.ClassDecl.ClassType;
import io.github.nickid2018.dejava.ast.TypeArgumentDecl.TypeArgumentBound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("AST Generation Test")
class AstTest {
    @Test
    @DisplayName("Full AST")
    void fullAstTest() {
        JavaSource source = new JavaSource();
        source
                .setPackage("io.github.nickid2018.dejava")
                .setClassDecl(
                        new ClassDecl("TestClass", ClassType.CLASS)
                                .addModifiers(new AnnotationDecl("Mixin"))
                                .addModifiers(new AnnotationDecl("Deprecated"))
                                .addModifiers(Modifiers.PUBLIC, Modifiers.ABSTRACT)
                                .addModifiers(Modifiers.SEALED)
                                .setExtends("ParentClass")
                                .setImplements(List.of(
                                        new Typename("Comparable", new TypeArgumentDecl("T")),
                                        Typename.of("INode")))
                                .setPermits(Typename.ofList("C1", "C2", "C3"))
                                .setTypeArguments(List.of(
                                        new TypeArgumentDecl("T", TypeArgumentBound.EXTENDS, "EntityPlayer")))
                );

        System.out.println(source.toSource());
    }
}
