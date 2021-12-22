package io.github.nickid2018.dejava.ast;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class VarDeclTest {
    @Test
    void testContainsName() {
        var result = new VarDecl("field0").toSource();
        assertTrue(result.contains("field0"));
        assertFalse(result.contains("="));
        System.out.println(result);
    }

    @Test
    void testInitializer() {
        var result = new VarDecl("field0", new VarInitializer()).toSource();
        assertTrue(result.contains("field0"));
        assertTrue(result.contains("="));
        System.out.println(result);
    }

    @Test
    void testDims() {
        var result = new VarDecl("field0", null, true).toSource();
        assertTrue(result.contains("field0"));
        assertTrue(result.contains("[]"));
        System.out.println(result);
    }
}
