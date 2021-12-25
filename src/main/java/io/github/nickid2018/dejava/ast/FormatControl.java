package io.github.nickid2018.dejava.ast;

public class FormatControl {

    private int indentSpaces;
    private boolean useSpace; // true ? space : tab

    private final static FormatControl defaultFC = new FormatControl();

    public static FormatControl getDefault() {
        return defaultFC;
    }

    public FormatControl() {
        reset();
    }

    public void reset() {
        setIndentSpaces(4);
        setUseSpace(true);
    }

    public void setUseSpace(boolean useSpace) {
        this.useSpace = useSpace;
    }

    public boolean isUseSpace() {
        return useSpace;
    }

    public int getIndentSpaces() {
        return indentSpaces;
    }

    public void setIndentSpaces(int indentSpaces) {
        this.indentSpaces = Math.max(0, indentSpaces);
    }
}
