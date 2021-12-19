package io.github.nickid2018.dejava.ast;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Typename implements INode {
    private final String identifier;
    private final List<TypeArgumentDecl> typeArguments;

    public Typename(String identifier, TypeArgumentDecl... typeArguments) {
        this.identifier = identifier;
        this.typeArguments = List.of(typeArguments);
    }

    public Typename(String identifier) {
        this(identifier, new TypeArgumentDecl[0]);
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

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .token(identifier)
                .doIf(typeArguments, (e, w) -> w.append(TypeArgumentDecl.listToSource(fc, e)))
                .toSource();
    }


}
