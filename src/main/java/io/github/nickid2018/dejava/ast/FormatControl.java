package io.github.nickid2018.dejava.ast;

public class FormatControl {

    private final static FormatControl defaultFC = new FormatControl();

    public static FormatControl getDefault() {
        return defaultFC;
    }
}
