package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.Modifiers;
import io.github.nickid2018.dejava.ast.TypeArgumentDecl.TypeArgumentBound;
import io.github.nickid2018.dejava.ast.classes.ClassDecl;

import org.junit.jupiter.api.*;

import java.util.*;

@DisplayName("AST Generation Test")
class AstTest {
    @Test
    @DisplayName("Full AST")
    void fullAstTest() {
        JavaSource source = new JavaSource();
        source
                .setPackage("io.github.nickid2018.dejava")
                .setClassDecl(
                        new ClassDecl("TestClass")
                                .addModifiers(new AnnotationDecl("Mixin"))
                                .addModifiers(new AnnotationDecl("Deprecated"))
                                .addModifiers(Modifiers.PUBLIC, Modifiers.ABSTRACT)
                                .addModifiers(Modifiers.SEALED)
                                .setExtends("ParentClass")
                                .setImplements(List.of(
                                        new Typename("Comparable",
                                                new TypeArgumentDecl(
                                                        "T")),
                                        Typename.of("INode")))
                                .setPermits(Typename.ofList("C1", "C2", "C3"))
                                .setTypeArguments(List.of(
                                        new TypeArgumentDecl("T",
                                                TypeArgumentBound.EXTENDS,
                                                "EntityPlayer"))));

        System.out.println(source.toSource());
    }
}
