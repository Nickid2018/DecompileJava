package io.github.nickid2018.dejava.ast;

import java.util.*;

public class ThrowList implements INode {
    private List<Typename> exceptionList = new ArrayList<>();

    @Override
    public String toSource(FormatControl fc) {
        return new SourceBuilder(fc)
                .appendIsolationText("throws")
                .appendAllNode(",", exceptionList) // TODO add missing ","
                .build();
    }

    public List<Typename> getExceptionList() {
        return exceptionList;
    }

    public ThrowList setExceptionList(List<Typename> exceptionList) {
        this.exceptionList = exceptionList;
        return this;
    }
}
