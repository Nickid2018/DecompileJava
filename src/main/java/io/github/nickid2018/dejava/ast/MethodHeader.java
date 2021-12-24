package io.github.nickid2018.dejava.ast;

import java.util.*;

public class MethodHeader implements INode, ITypeArguments {
    private List<TypeArgumentDecl> typeArguments = new ArrayList<>();
    private Typename resultType; // UnannType or void
    private String identifier;
    // TODO
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


    @Override
    public String toSource(FormatControl fc) {
        return null;
    }
}
