package io.github.nickid2018.dejava.util;

import io.github.nickid2018.dejava.ast.*;

import java.util.*;

public class ModifierUtil {
    private ModifierUtil() {
        throw new IllegalCallerException();
    }
    public static String toString(List<IModifier> modifiers) {
        return modifiers
                .stream()
                .sorted()
                .reduce(
                        "",
                        (cur, acc) -> cur + acc.toSource() + (acc.isAnnotation() ? "\n" : " "),
                        (a, b) -> a + b)
                .stripTrailing();
    }
}
