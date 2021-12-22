package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;


class FieldDeclTest {
    @Test
    void test() {
        var result = new FieldDecl(Typename.of("int"), "field0", null)
            .addModifiers(Modifiers.PUBLIC).toSource();
        assertEquals("public int field0", result);

        var result2 = new FieldDecl(Typename.of("int"), 
            Map.of("field1", new IExpression(){}, 
                   "field2", new IExpression(){}))
            .addModifiers(Modifiers.PUBLIC).toSource();
        
        System.out.println(result);
        System.out.println(result2);

    }
    // public int field0[];
}
