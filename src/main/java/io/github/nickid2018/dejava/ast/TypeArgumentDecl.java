package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.ConstantNames;

import java.util.List;
import java.util.stream.Collectors;

public class TypeArgumentDecl implements INode /*, IModifiable */ {

    private String identifier;
    private TypeArgumentBound bound;
    private String boundType;

    public TypeArgumentDecl(String identifier, TypeArgumentBound bound, String boundType) {
        this.identifier = identifier;
        this.bound = bound;
        this.boundType = boundType;
    }

    public TypeArgumentDecl(String identifier) {
        this(identifier, null, null);
    }

    public static String listToSource(FormatControl fc, List<TypeArgumentDecl> decls) {
        return new StructuralWriter(fc)
                .append("<")
                // stop tokenSep from adding leading space
                .append(decls.stream().map(e -> e.toSource(fc)).collect(Collectors.joining(", ")))
                .append(">")
                .toSource();
    }

    public String getIdentifier() {
        return identifier;
    }

    public TypeArgumentDecl setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public TypeArgumentBound getBound() {
        return bound;
    }

    public TypeArgumentDecl setBound(TypeArgumentBound bound) {
        this.bound = bound;
        return this;
    }

    public String getBoundType() {
        return boundType;
    }

    public TypeArgumentDecl setBoundType(String boundType) {
        this.boundType = boundType;
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .token(identifier)
                .doIf(boundType, (e, w) -> w.token(bound.keyword, boundType))
                .toSource();
    }

    public enum TypeArgumentBound {
        EXTENDS(ConstantNames.EXTENDS), SUPER(ConstantNames.SUPER);

        public final String keyword;

        TypeArgumentBound(String keyword) {
            this.keyword = keyword;
        }
    }
}
