package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.source.Annotation;
import io.github.nickid2018.dejava.source.IdentifierProvider;

import java.util.List;

public interface PrimitiveType extends Type, IdentifierProvider, ArraySupportable {
    static BooleanType newBoolean(List<Annotation> annotations) {
        return new BooleanType(annotations);
    }

    static FloatingPointType newFloat(NumericType.Float type, List<Annotation> annotations) {
        return new FloatingPointType(type, annotations);
    }

    static IntegralType newInt(NumericType.Int type, List<Annotation> annotations) {
        return new IntegralType(type, annotations);
    }
}
