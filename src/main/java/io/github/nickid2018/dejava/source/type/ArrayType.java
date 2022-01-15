package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.source.TypeFormats;

public class ArrayType<T extends Type & ArraySupportable> implements Type {

    protected T type;
    protected Dims dims;

    public ArrayType(T type, Dims dims) {
        this.type = type;
        this.dims = dims;
    }

    public T type() {
        return type;
    }

    public ArrayType<T> type(T type) {
        this.type = type;
        return this;
    }

    public Dims dims() {
        return dims;
    }

    public ArrayType<T> dims(Dims dims) {
        this.dims = dims;
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return TypeFormats.format(fc, this);
    }
}
