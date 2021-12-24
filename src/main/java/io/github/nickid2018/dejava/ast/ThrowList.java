package io.github.nickid2018.dejava.ast;

public class ThrowList implements INode {
    // TODO add missing exceptionTypeList
    @Override
    public String toSource(FormatControl fc) {
        return "throws ";
    }
}
