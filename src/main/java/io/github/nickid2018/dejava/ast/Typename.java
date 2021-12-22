package io.github.nickid2018.dejava.ast;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.github.nickid2018.dejava.ConstantNames;

public class Typename implements INode {
    private String identifier;
    private List<TypeArgumentDecl> typeArguments;
    private boolean isArray;

    public Typename(String identifier, TypeArgumentDecl... typeArguments) {
        this.identifier = identifier;
        this.typeArguments = List.of(typeArguments);
    }

    public Typename(String identifier) {
        this(identifier, new TypeArgumentDecl[0]);
    }

    public Typename setArray(boolean isArray) {
        this.isArray = isArray;
        return this;
    }

    public boolean isArray() {
        return isArray;
    }

    public static Typename of(String s) {
        return new Typename(s);
    }

    public static List<Typename> ofList(String... s) {
        return Arrays.stream(s).map(Typename::of).collect(Collectors.toList());
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<TypeArgumentDecl> getTypeArguments() {
        return typeArguments;
    }

    public boolean isPrimitive() {
        return switch(identifier) {
            case ConstantNames.BOOLEAN,
                ConstantNames.BYTE,
                ConstantNames.SHORT,
                ConstantNames.INT,
                ConstantNames.LONG,
                ConstantNames.CHAR,
                ConstantNames.FLOAT,
                ConstantNames.DOUBLE -> true;
            default -> false;
        };
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .token(identifier)
                .doIfTrue(!isPrimitive(), (ww) -> 
                    ww.doIf(typeArguments, (e, w) -> w.append(TypeArgumentDecl.listToSource(fc, e))))
                .doIfTrue(isArray, (w) -> w.append("[]"))
                .toSource();
    }

}
