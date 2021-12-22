package io.github.nickid2018.dejava.ast;

import java.util.*;

public class VarDecl implements INode {
    private final String identifier;
    private final VarInitializer varInitializer;
    private final boolean dims;

    public VarDecl(String identifier,
                   VarInitializer varInitializer,
                   boolean dims) {
        this.identifier = Objects.requireNonNull(identifier);
        this.varInitializer = varInitializer;
        this.dims = dims;
    }

    public VarDecl(String identifier, VarInitializer varInitializer) {
        this(identifier, varInitializer, false);
    }

    public VarDecl(String identifier) {
        this(identifier, null, false);
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .token(identifier)
                .doIfTrue(dims, (sw1) -> sw1.append("[]"))
                .doIf(varInitializer, (node, sw1) ->
                        sw1.token("=", varInitializer.toSource(fc)))
                .toSource();
    }
}
