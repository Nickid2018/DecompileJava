package io.github.nickid2018.dejava.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.github.nickid2018.dejava.ast.TypeArgumentDecl.TypeArgumentBound;

public class TypenameTest {

    @Test
    void testType() {
        Typename t = new Typename("List", new TypeArgumentDecl("?", TypeArgumentBound.EXTENDS, "String"));
        t.setArray(true);
        assertEquals(t.toSource(), "List<? extends String>[]");
    }

    @Test
    void testPrimitiveType() {
        Typename t = new Typename("short", new TypeArgumentDecl("?", TypeArgumentBound.EXTENDS, "String"));
        t.setArray(true);
        assertTrue(t.isPrimitive());
        assertEquals(t.toSource(), "short[]");

        t.setArray(false);
        assertEquals(t.toSource(), "short");
    }
}
