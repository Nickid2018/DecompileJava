package io.github.nickid2018.dejava.ast;

import org.junit.jupiter.api.Test;

public class StructuralWriterTest {
    @Test
    void testToSource() {
        StructuralWriter w = new StructuralWriter(FormatControl.getDefault());
        String t = w
                .token("public", "static", "void")
                .token("main(String[] args)")
                .block(e -> e.token("String", "s;").line().token("aaa"))
                .toSource();
        System.out.println(t);
    }
}
