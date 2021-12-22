package io.github.nickid2018.dejava.ast;

import java.util.*;

public interface ITypeArguments {
    List<TypeArgumentDecl> getTypeArguments();

    ITypeArguments setTypeArguments(List<TypeArgumentDecl> typeArguments);
}
