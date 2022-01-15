package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.source.Annotation;
import io.github.nickid2018.dejava.source.TypeFormats;

import java.util.List;

public class FloatingPointType extends AbstractType<FloatingPointType> implements NumericType {
    protected Float type;

    public Float type() {
        return type;
    }

    public FloatingPointType type(Float type) {
        this.type = type;
        return this;
    }

    public FloatingPointType(Float type, List<Annotation> annotations) {
        super(annotations);
        this.type = type;
    }

    @Override
    public String identifier() {
        return NumericType.Float.toString(type);
    }

    @Override
    public String toSource(FormatControl fc) {
        return TypeFormats.format(fc, this);
    }
}
