package io.github.nickid2018.dejava.ast;

import java.util.List;

public interface IModifiable {
    List<IModifier> getModifiers();

    IModifiable addModifiers(IModifier... modifiers);
}
