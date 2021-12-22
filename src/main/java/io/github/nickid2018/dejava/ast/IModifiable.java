package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.IModifier;
import io.github.nickid2018.dejava.util.ModifierUtil;

import java.util.List;

public interface IModifiable {
    List<IModifier> getModifiers();

    IModifiable addModifiers(IModifier... modifiers);

    default String getModifiersString() {
        return ModifierUtil.toString(getModifiers());
    }
}
