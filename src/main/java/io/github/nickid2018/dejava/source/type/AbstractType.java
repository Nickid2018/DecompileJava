package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.source.Annotation;

import java.util.List;

public abstract class AbstractType<T extends AbstractType> implements Type {
    protected List<Annotation> annotations;

    public AbstractType(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public List<Annotation> annotations() {
        return annotations;
    }

    public T annotations(List<Annotation> annotations) {
        this.annotations = annotations;
        return (T) this;
    }
}
