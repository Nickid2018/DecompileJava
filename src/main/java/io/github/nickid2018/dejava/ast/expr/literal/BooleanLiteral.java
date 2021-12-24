package io.github.nickid2018.dejava.ast.expr.literal;

import io.github.nickid2018.dejava.ast.FormatControl;

public class BooleanLiteral implements Literal<Boolean> {

    private boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    @Override
    public String toSource(FormatControl fc) {
        return Boolean.toString(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public BooleanLiteral setValue(Boolean value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanLiteral o && o.value == value;
    }
}
