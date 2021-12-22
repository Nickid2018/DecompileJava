package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;

import java.util.*;

public class FieldDecl implements INode, IModifiable {
    protected ModifierList modifiers = new ModifierList();
    private final Typename typename;
    private final List<VarDecl> varDeclList;

    public FieldDecl(Typename typename, List<VarDecl> varDeclList) {
        this.typename = Objects.requireNonNull(typename);
        this.varDeclList = Objects.requireNonNull(varDeclList);
    }

    @Override
    public FieldDecl addModifiers(IModifier... modifiers) {
        this.modifiers.addModifiers(modifiers);
        return this;
    }

    @Override
    public List<IModifier> getModifiers() {
        return modifiers.getModifiers();
    }

    public Typename getTypename() {
        return typename;
    }

    public List<VarDecl> getVarDeclList() {
        return varDeclList;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .append(getModifiersString())
                .token(getTypename().toSource(fc))
                .token(getVarDeclList())
                .toSource();
    }
}