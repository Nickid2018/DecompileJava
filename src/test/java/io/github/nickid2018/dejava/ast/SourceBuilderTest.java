package io.github.nickid2018.dejava.ast;

import org.junit.jupiter.api.*;

import java.util.*;

public class SourceBuilderTest {

    @Test
    void testAutoIndent() {
        Assertions.assertTrue(
                "".equals(new SourceBuilder().build()));
        System.out.println(new SourceBuilder()
                .appendText("requires 'name'")
                .build());
        System.out.println(new SourceBuilder()
                .appendText("function sum(a, b)")
                .breakLine()
                .child((wr1)-> wr1.appendText("return a+b"))
                .breakLine()
                .appendText("end")
                .build());
    }
}
