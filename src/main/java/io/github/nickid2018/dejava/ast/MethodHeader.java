package io.github.nickid2018.dejava.ast;

import java.util.*;

public class MethodHeader implements INode, ITypeArguments {
    private List<TypeArgumentDecl> typeArguments = new ArrayList<>();
    // TODO add missing initializer
    private Typename resultType; // UnannType or void
    private String identifier;
    // TODO parList
    private ThrowList throwList = new ThrowList();

    public String getIdentifier() {
        return identifier;
    }

    public MethodHeader setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }
    @Override
    public List<TypeArgumentDecl> getTypeArguments() {
        return typeArguments;
    }

    @Override
    public MethodHeader setTypeArguments(List<TypeArgumentDecl> typeArguments) {
        this.typeArguments = typeArguments;
        return this;
    }

    public MethodHeader setThrowList(ThrowList throwList) {
        this.throwList = throwList;
        return this;
    }

    public ThrowList getThrowList() {
        return throwList;
    }

    public MethodHeader setResultType(Typename resultType) {
        this.resultType = resultType;
        return this;
    }

    public Typename getResultType() {
        return resultType;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .doIf(typeArguments, (s, w) -> w.append(TypeArgumentDecl.listToSource(fc, s)))
                .token(resultType)
                .token(identifier)
                .append("()")
                .token(throwList)
                .line()
                .toSource();
    }
}
