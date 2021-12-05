package io.github.nickid2018.dejava;

public class Checkers {

    public static void checkIfTrue(boolean check, String error, Object... args) throws DecompileException {
        if (check)
            throw new DecompileException(error, args);
    }

    public static void checkIfFalse(boolean check, String error, Object... args) throws DecompileException {
        if (!check)
            throw new DecompileException(error, args);
    }
}
