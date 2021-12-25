package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.ast.TypeArgumentDecl.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowListTest {

    @Test
    void test() {
        System.out.println(new ThrowList()
                .setExceptionList(List.of(
                        new Typename("NullPointException"),
                        new Typename("IndexOutOfBoundsException")))
                .toSource());
    }
}
