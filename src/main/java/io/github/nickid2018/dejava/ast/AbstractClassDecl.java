package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;

import java.util.*;

public sealed abstract class AbstractClassDecl<T extends AbstractClassDecl<?>> implements INode, IModifiable, ITypeArguments,
        IPermits, IImplemetns permits ClassDecl, EnumDecl {
    protected ModifierList modifiers = new ModifierList();
    protected String identifier;
    protected List<TypeArgumentDecl> typeArguments;
    protected List<Typename> classImplements = new ArrayList<>();
    protected List<Typename> classPermits = new ArrayList<>();

    public AbstractClassDecl(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public T addModifiers(IModifier... modifier) {
        modifiers.addModifiers(modifier);
        return (T) this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public List<Typename> getImplements() {
        return classImplements;
    }

    @Override
    public T setImplements(List<Typename> classImplements) {
        this.classImplements = classImplements;
        return (T) this;
    }

    @Override
    public List<IModifier> getModifiers() {
        return this.modifiers.getModifiers();
    }

    @Override
    public List<Typename> getPermits() {
        return classPermits;
    }

    public T setPermits(List<Typename> classPermits) {
        this.classPermits = classPermits;
        return (T) this;
    }

    @Override
    public List<TypeArgumentDecl> getTypeArguments() {
        return typeArguments;
    }

    @Override
    public T setTypeArguments(List<TypeArgumentDecl> typeArguments) {
        this.typeArguments = typeArguments;
        return (T) this;
    }
}
