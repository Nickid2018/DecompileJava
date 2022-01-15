package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.source.Annotation;
import io.github.nickid2018.dejava.source.TypeFormats;

import java.util.List;

public class ClassType extends AbstractType<ClassType> implements ReferenceType, ClassTypePrefix {
    protected boolean isInterface;
    protected String identifier;
    protected ClassTypePrefix prefix;

    public ClassType(List<Annotation> annotations, boolean isInterface, String identifier, ClassTypePrefix prefix) {
        super(annotations);
        this.isInterface = isInterface;
        this.identifier = identifier;
        this.prefix = prefix;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public ClassType setInterface(boolean anInterface) {
        isInterface = anInterface;
        return this;
    }

    @Override
    public String identifier() {
        return identifier;
    }

    public ClassType identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public ClassTypePrefix prefix() {
        return prefix;
    }

    public ClassType prefix(ClassTypePrefix prefix) {
        this.prefix = prefix;
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return TypeFormats.format(fc, this);
    }
}
