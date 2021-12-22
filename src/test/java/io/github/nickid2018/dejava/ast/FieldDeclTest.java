package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class FieldDeclTest {
    @Test
    void test() {

        var result = new FieldDecl(Typename.of("int"),
                List.of(new VarDecl("field0", null, true))
        ).addModifiers(Modifiers.PUBLIC).toSource();
        assertTrue(result.contains("field0"));
        assertTrue(result.contains("[]"));
        System.out.println(result);
    }
    // public int field0[];
}
