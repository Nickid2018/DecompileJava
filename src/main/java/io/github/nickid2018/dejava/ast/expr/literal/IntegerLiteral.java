package io.github.nickid2018.dejava.ast.expr.literal;

import io.github.nickid2018.dejava.ast.FormatControl;

public class IntegerLiteral implements Literal<Long> {

    private long value;
    private boolean isLong = false;
    // TODO: hex, octal and binary
    
    public IntegerLiteral(int value) {
           this.value = value;
    } 

    public IntegerLiteral(long value2) {
    }

    @Override
    public String toSource(FormatControl fc) {
        return Long.toString(value) + (isLong ? "L" : "");
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public IntegerLiteral setValue(Long value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerLiteral o && o.value == value;
    }

    public boolean isLong() {
        return isLong;
    }

    public void setLong(boolean isLongType) {
        this.isLong = isLongType;
    }

}