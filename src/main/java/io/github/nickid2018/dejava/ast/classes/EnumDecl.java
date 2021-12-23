package io.github.nickid2018.dejava.ast.classes;

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.ast.StructuralWriter;

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
