package io.github.nickid2018.dejava;

public class DecompileException extends RuntimeException {

    public DecompileException(String info) {
        super(info);
    }

    public DecompileException(String template, Object... args) {
        super(template.formatted(args));
    }
}
