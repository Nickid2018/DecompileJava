package io.github.nickid2018.dejava.ast.expr.literal;

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.ast.FormatControl;

public class NullLiteral implements Literal<Object> {

    /**
     * Use this singleton instance to represent null literal
     * to prevent unnecessary object creation.
     * 
     * @see Literal#ofNull()
     */
    public static final NullLiteral INSTANCE = new NullLiteral();

    @Override
    public String toSource(FormatControl fc) {
        return ConstantNames.NULL;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public Literal<Object> setValue(Object value) {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NullLiteral;
    }
    
}
