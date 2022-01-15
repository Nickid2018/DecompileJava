package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.source.Annotation;
import io.github.nickid2018.dejava.source.IdentifierProvider;
import io.github.nickid2018.dejava.source.TypeFormats;

import java.util.List;

public class TypeVariable extends AbstractType<TypeVariable> implements IdentifierProvider, ArraySupportable {
    protected String identifier;

    public TypeVariable(List<Annotation> annotations, String identifier) {
        super(annotations);
        this.identifier = identifier;
    }

    @Override
    public String identifier() {
        return identifier;
    }

    public TypeVariable identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public List<Annotation> annotations() {
        return annotations;
    }

    public TypeVariable annotations(List<Annotation> annotations) {
        this.annotations = annotations;
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return TypeFormats.format(fc, this);
    }
}
