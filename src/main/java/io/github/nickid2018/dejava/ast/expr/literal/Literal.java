package io.github.nickid2018.dejava.ast.expr.literal;

import io.github.nickid2018.dejava.ast.expr.IExpression;

/**
 * Literal values node.
 */
public interface Literal<T> extends IExpression {
    T getValue();
    Literal<T> setValue(T value);

    static Literal<Boolean> of(boolean value) {
        return new BooleanLiteral(value);
    }

    static Literal<Long> of(long value) {
        return new IntegerLiteral(value);
    }

    static Literal<Long> of(int value) {
        return new IntegerLiteral(value);
    }

    static Literal<Object> ofNull() {
        return NullLiteral.INSTANCE;
    }



    
}
