package io.github.nickid2018.dejava.ast;

import org.junit.jupiter.api.*;

import java.util.*;

public class MethodHeaderTest {

    @Test
    void test() {
        System.out.println(new MethodHeader()
                .setTypeArguments(List.of(new TypeArgumentDecl("T",
                        TypeArgumentDecl.TypeArgumentBound.EXTENDS,
                        "EntityPlayer")))
                .setResultType(new Typename("void"))
                .setIdentifier("method0")
                .setThrowList(new ThrowList()
                        .setExceptionList(List.of(
                                new Typename("NullPointException"),
                                new Typename("Exception")
                                )))
                .toSource());
    }
}
