package io.github.nickid2018.dejava.ast;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class StructuralWriterTest {
    @Test
    void testToSource() {
        StructuralWriter w = new StructuralWriter(FormatControl.getDefault());
        String t = w
                .token("public", "static", "void")
                .token("main(String[] args)")
                .block(e -> e.token("String", "s;").line().token("aaa"))
                .toSource();

        assertTrue(t.contains("public"));
        assertTrue(t.contains("static"));
        assertTrue(t.contains("void"));
        assertTrue(t.contains("main(String[] args)"));
        assertTrue(t.contains("String"));
        assertTrue(t.contains("s"));
        assertTrue(t.contains("aaa"));

        System.out.println(t);
    }

    private void printStart() {
        System.out.println("----- START -----");
    }

    private void printEnd() {
        System.out.println("----- END -----");
    }

    @Test
    void testAppend() {
        printStart();
        System.out.println(StructuralWriter.newInstance().append("abc").toSource());
        printEnd();
        printStart();
        System.out.println(StructuralWriter.newInstance().append("abc").line().append("def").toSource());
        printEnd();
        System.out.println(StructuralWriter.newInstance().append("modifier").token("id").block((wr)->{
            wr.append("modifier").token("id").block((wr2)->{
                        wr2.append("code body");
                    });
        }).toSource());
    }
}
