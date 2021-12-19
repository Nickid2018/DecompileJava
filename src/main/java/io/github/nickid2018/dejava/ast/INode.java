package io.github.nickid2018.dejava.ast;

public interface INode {
    String toSource(FormatControl fc);

    default String toSource() {
        return toSource(FormatControl.getDefault());
    }
}
