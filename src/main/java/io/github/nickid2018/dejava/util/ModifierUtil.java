package io.github.nickid2018.dejava.util;

import io.github.nickid2018.dejava.api.IModifier;

import java.util.*;
import java.util.stream.*;

public class ModifierUtil {
    private ModifierUtil() {
        throw new IllegalCallerException();
    }

    public static boolean checkFlag(int accessFlag, int flag) {
        return (accessFlag & flag) != 0;
    }

    public static String toString(IModifier... modifiers) {
        return toString(Arrays.stream(modifiers));
    }

    public static String toString(Stream<IModifier> modifiers) {
       return modifiers.sorted()
                .reduce(
                        "",
                        (cur, acc) -> cur + acc.toSource() + (acc.isAnnotation() ? "\n" : " "),
                        (a, b) -> a + b)
                .stripTrailing();
    }

    public static String toString(List<IModifier> modifiers) {
        return toString(modifiers.stream());
    }
}
