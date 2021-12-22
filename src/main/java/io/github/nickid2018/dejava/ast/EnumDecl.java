package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.ConstantNames;

public final class EnumDecl extends AbstractClassDecl<EnumDecl> {
    public EnumDecl(String identifier) {
        super(identifier);
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .append(getModifiersString())
                .token(ConstantNames.ENUM)
                .toSource();
    }
}
