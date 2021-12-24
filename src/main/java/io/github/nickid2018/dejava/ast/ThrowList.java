package io.github.nickid2018.dejava.ast;

import java.util.*;

public class ThrowList implements INode {
    private List<Typename> exceptionList;

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .token("throws")
                .token(exceptionList) // TODO add missing ","
                .toSource();
    }

    public List<Typename> getExceptionList() {
        return exceptionList;
    }

    public ThrowList setExceptionList(List<Typename> exceptionList) {
        this.exceptionList = exceptionList;
        return this;
    }
}
