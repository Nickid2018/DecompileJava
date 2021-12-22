package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.*;

public enum ClassType {
    CLASS(ConstantNames.CLASS),
    INTERFACE(ConstantNames.INTERFACE),
    ENUM(ConstantNames.ENUM),
    ANNOTATION(ConstantNames.ANNOTATION),
    RECORD(ConstantNames.RECORD);

    public final String keyword;

    ClassType(String t) {
        this.keyword = t;
    }
}
