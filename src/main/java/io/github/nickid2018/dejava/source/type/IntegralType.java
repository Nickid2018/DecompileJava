package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.source.Annotation;
import io.github.nickid2018.dejava.source.TypeFormats;

import java.util.List;

public class IntegralType extends AbstractType<IntegralType> implements NumericType {
    protected Int type;

    public Int type() {
        return type;
    }

    public IntegralType type(Int type) {
        this.type = type;
        return this;
    }

    public IntegralType(Int type, List<Annotation> annotations) {
        super(annotations);
        this.type = type;
    }

    @Override
    public String identifier() {
        return NumericType.Int.toString(type);
    }

    @Override
    public String toSource(FormatControl fc) {
        return TypeFormats.format(fc, this);
    }

}
