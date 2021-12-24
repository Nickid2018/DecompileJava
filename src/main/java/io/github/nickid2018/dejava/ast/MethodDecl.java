package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;

import java.util.*;

public class MethodDecl implements INode, IModifiable {
    protected ModifierList modifiers = new ModifierList();
    private MethodHeader header;

    public MethodHeader getHeader() {
        return header;
    }

    public MethodDecl setHeader(MethodHeader header) {
        this.header = header;
        return this;
    }

    @Override
    public List<IModifier> getModifiers() {
        return this.modifiers.getModifiers();
    }

    @Override
    public MethodDecl addModifiers(IModifier... modifiers) {
        this.modifiers.addModifiers(modifiers);
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc).append(getModifiersString())
                .toSource();
    }
}
