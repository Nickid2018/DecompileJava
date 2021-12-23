package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;

import java.util.*;

public class EnumConstant implements INode, IModifiable {
    protected ModifierList modifiers = new ModifierList();
    private String identifier;
    // TODO ParameterList parameters;
    // TODO ClassBody classBody

    public EnumConstant(String identifier) {
        this.identifier = identifier;
    }

    public EnumConstant setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public List<IModifier> getModifiers() {
        return this.modifiers.getModifiers();
    }

    @Override
    public EnumConstant addModifiers(IModifier... modifiers) {
        this.modifiers.addModifiers(modifiers);
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .append(getModifiersString())
                .token(identifier)
                // TODO block enumBody
                .toSource();
    }
}
