package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.*;
import io.github.nickid2018.dejava.api.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ModuleDirectiveTest {
    @Test
    void testRequires() {
        var result = new ModuleDirective()
                .setType(ModuleDirective.Type.Requires)
                .addModifiers(Modifiers.TRANSITIVE)
                .setModuleNameList(List.of("abc.def"))
                .toSource();

        System.out.println(result);
        assertTrue(result.contains(ConstantNames.REQUIRES));
        assertTrue(result.contains(ConstantNames.TRANSITIVE));
        assertTrue(result.contains("abc.def"));

        result = new ModuleDirective()
                .setType(ModuleDirective.Type.Requires)
                .addModifiers(Modifiers.TRANSITIVE)
                .toSource();
        System.out.println(result);

        assertTrue(result.contains(ConstantNames.REQUIRES));
        assertTrue(result.contains(ConstantNames.TRANSITIVE));
        assertFalse(result.contains("abc.def"));
    }

    @Test
    void testOpens() {
        var result = new ModuleDirective()
                .setType(ModuleDirective.Type.Opens)
                .setPackageName("com.xxx")
                .setModuleNameList(List.of("abc.def"))
                .toSource();

        System.out.println(result);
        assertTrue(result.contains(ConstantNames.OPENS));
        assertTrue(result.contains("com.xxx"));
        assertTrue(result.contains("abc.def"));
        assertTrue(result.contains(ConstantNames.TO));
    }

    @Test
    void testExports() {
        var result = new ModuleDirective()
                .setType(ModuleDirective.Type.Exports)
                .setPackageName("com.xxx")
                .setModuleNameList(List.of("abc.def", "abc.def2"))
                .toSource();

        System.out.println(result);
        assertTrue(result.contains(ConstantNames.EXPORTS));
        assertTrue(result.contains(ConstantNames.TO));
        assertTrue(result.contains("com.xxx"));
        assertTrue(result.contains("abc.def"));
    }

    @Test
    void testProvides() {
        var result = new ModuleDirective()
                .setType(ModuleDirective.Type.Provides)
                .setTypename(new Typename("java.lang.String"))
                .setTypenameList((List.of(new Typename("java.lang.Object"))))
                .toSource();

        System.out.println(result);
        assertTrue(result.contains(ConstantNames.PROVIDES));
        assertTrue(result.contains(ConstantNames.WITH));
        assertTrue(result.contains("java.lang.Object"));
        assertTrue(result.contains("java.lang.String"));
    }

    @Test
    void testUses() {
        var result = new ModuleDirective()
                .setType(ModuleDirective.Type.Uses)
                .setTypename(new Typename("java.lang.String"))
                .toSource();

        System.out.println(result);
        assertTrue(result.contains(ConstantNames.USES));
        assertTrue(result.contains("java.lang.String"));
    }
}
