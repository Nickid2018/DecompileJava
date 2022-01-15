package io.github.nickid2018.dejava.util;

public class Debug {
    public static void log(Object... objects) {
        for (var obj : objects) {
            System.out.print(obj + " ");
        }
        if (objects != null && objects.length > 0) {
            System.out.println();
        }
    }
}
