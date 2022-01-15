package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.ast.INode;
import io.github.nickid2018.dejava.source.Annotation;
import io.github.nickid2018.dejava.source.TypeFormats;

import java.util.List;

public class Dims implements INode {
    protected List<Annotation> annotations;

    public List<Annotation> annotations() {
        return annotations;
    }

    public Dims annotations(List<Annotation> annotations) {
        this.annotations = annotations;
        return this;
    }

    public Dims(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    @Override
    public String toSource(FormatControl fc) {
        return TypeFormats.format(fc, this);
    }
}
