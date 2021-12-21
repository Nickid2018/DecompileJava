package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModifierList {
    private final ArrayList<IModifier> modifiers = new ArrayList<>();

    public ModifierList addModifiers(IModifier... modifier) {
        modifiers.addAll(Arrays.asList(modifier));
        return this;
    }

    public List<IModifier> getModifiers() {
        return modifiers;
    }

    public String toString() {
        return ModifierUtil.toString(modifiers);
    }

}
