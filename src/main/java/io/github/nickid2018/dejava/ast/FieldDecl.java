package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;

import java.util.*;
import java.util.stream.Collectors;

public class FieldDecl implements INode, IModifiable {
    protected ModifierList modifiers = new ModifierList();
    private Typename typename;
    private Map<String, IExpression> declarators;

    public FieldDecl(Typename typename, Map<String, IExpression> varDeclList) {
        this.typename = Objects.requireNonNull(typename);
        this.declarators = Objects.requireNonNull(varDeclList);
    }

    public FieldDecl(Typename typename, String initializer, IExpression expr) {
        this.typename = Objects.requireNonNull(typename);
        this.declarators = new HashMap<>();
        this.declarators.put(initializer, expr);
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

    public Map<String, IExpression> getDeclarators() {
        return declarators;
    }

    public FieldDecl addDeclarator(String identifier, IExpression initializer) {
        declarators.put(identifier, initializer);
        return this;
    }

    private String declToString(FormatControl fc) {
        return declarators.entrySet().stream()
            .map(e -> e.getKey() + (e.getValue() != null ? (" = " + e.getValue().toSource(fc)) : ""))
            .collect(Collectors.joining(", "));
    }


    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .append(getModifiersString())
                .token(getTypename().toSource(fc))
                .token(declToString(fc))
                .toSource();
    }
}