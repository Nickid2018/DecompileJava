package io.github.nickid2018.dejava.util;

import io.github.nickid2018.dejava.api.IModifier;

import java.util.List;

public class ModifierUtil {
    private ModifierUtil() {
        throw new IllegalCallerException();
    }

    public static boolean checkFlag(int accessFlag, int flag) {
        return (accessFlag & flag) != 0;
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
