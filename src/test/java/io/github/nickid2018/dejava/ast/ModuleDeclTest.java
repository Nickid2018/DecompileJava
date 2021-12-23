package io.github.nickid2018.dejava.ast;

import org.junit.jupiter.api.*;

import java.util.*;

class ModuleDeclTest {
    @Test
    void testRequires() {
        var result = new ModuleDecl()
                .setOpen(false)
                .setIdentifier("modulename")
                .setModuleDirectives(List.of(
                        new ModuleDirective()
                                .setType(ModuleDirective.Type.Requires)
                                .setModuleNameList(List.of("java.base")),
                        new ModuleDirective()
                                .setType(ModuleDirective.Type.Requires)
                                .setModuleNameList(List.of("guava")),
                        new ModuleDirective()
                                .setType(ModuleDirective.Type.Exports)
                                .setPackageName("abc.def")
                                .setModuleNameList(List.of("targetmodulename"))
                )).toSource();
        System.out.println(result);
    }
}
