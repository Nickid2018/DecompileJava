package io.github.nickid2018.dejava.ast;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class EnumConstTest {
    @Test
    void test() {
        var result = new EnumConstant("WINDOWS").toSource();
        assertTrue(result.contains("WINDOWS"));
        System.out.println(result);
    }
}
