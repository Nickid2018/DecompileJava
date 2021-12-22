/*
 * Copyright 2021 Nickid2018
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;

import java.util.*;

public class FieldDecl implements INode, IModifiable {
    protected ModifierList modifiers = new ModifierList();
    private final Typename typename;
    private final List<VarDecl> varDeclList;

    public FieldDecl(Typename typename, List<VarDecl> varDeclList) {
        this.typename = Objects.requireNonNull(typename);
        this.varDeclList = Objects.requireNonNull(varDeclList);
    }

    @Override
    public FieldDecl addModifiers(IModifier... modifiers) {
        this.modifiers.addModifiers(modifiers);
        return this;
    }

    @Override
    public List<IModifier> getModifiers() {
        return modifiers.getModifiers();
    }

    public Typename getTypename() {
        return typename;
    }

    public List<VarDecl> getVarDeclList() {
        return varDeclList;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .append(getModifiersString())
                .token(getTypename().toSource(fc))
                .token(getVarDeclList())
                .toSource();
    }
}