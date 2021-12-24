package io.github.nickid2018.dejava.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.nickid2018.dejava.ast.Typename;

public class DescriptorUtilsTest {
    @Test
    void testObjectTypename() {
        String t = "[[Ljava/lang/String;";
        Typename type = new Typename("String").setArrayDim(2);
        var desc = DescriptorUtils.toTypename(t);
        assertEquals(t, type.toDescriptor("java/lang/String"));
        System.out.println(desc.toSource());
    }

    @Test
    void testPrimitiveTypename() {
        String t = "Z", t2 = "[[[[[[I";
        Typename type = new Typename("boolean");

        var desc = DescriptorUtils.toTypename(t);
        assertEquals(t, type.toDescriptor(null));
        System.out.println(desc.toSource());

        var desc2 = DescriptorUtils.toTypename(t2);
        assertEquals(desc2.getArrayDim(), 6);
        assertEquals(desc2.getIdentifier(), "int");
        System.out.println(desc2.toSource());
    }
}
