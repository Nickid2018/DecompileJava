package io.github.nickid2018.dejava.ast;

public final class EnumDecl extends AbstractClassDecl<EnumDecl> {
    public EnumDecl(String identifier) {
        super(identifier);
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .append(getModifiersString())
                .token("enum")
                .toSource();
    }
}
