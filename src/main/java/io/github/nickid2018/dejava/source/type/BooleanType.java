package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.source.Annotation;
import io.github.nickid2018.dejava.source.TypeFormats;

import java.util.List;

public class BooleanType extends AbstractType<BooleanType> implements PrimitiveType {
    public BooleanType(List<Annotation> annotations) {
        super(annotations);
    }

    @Override
    public String identifier() {
        return ConstantNames.BOOLEAN;
    }

    @Override
    public String toSource(FormatControl fc) {
        return TypeFormats.format(fc, this);
    }
}
