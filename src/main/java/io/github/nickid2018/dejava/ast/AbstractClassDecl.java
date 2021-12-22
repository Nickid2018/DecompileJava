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

public sealed abstract class AbstractClassDecl<T extends AbstractClassDecl<?>> implements INode, IModifiable, ITypeArguments,
        IPermits, IImplemetns permits ClassDecl, EnumDecl {
    protected ModifierList modifiers = new ModifierList();
    protected String identifier;
    protected List<TypeArgumentDecl> typeArguments;
    protected List<Typename> classImplements = new ArrayList<>();
    protected List<Typename> classPermits = new ArrayList<>();

    public AbstractClassDecl(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public T addModifiers(IModifier... modifier) {
        modifiers.addModifiers(modifier);
        return (T) this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public List<Typename> getImplements() {
        return classImplements;
    }

    @Override
    public T setImplements(List<Typename> classImplements) {
        this.classImplements = classImplements;
        return (T) this;
    }

    @Override
    public List<IModifier> getModifiers() {
        return this.modifiers.getModifiers();
    }

    @Override
    public List<Typename> getPermits() {
        return classPermits;
    }

    public T setPermits(List<Typename> classPermits) {
        this.classPermits = classPermits;
        return (T) this;
    }

    @Override
    public List<TypeArgumentDecl> getTypeArguments() {
        return typeArguments;
    }

    @Override
    public T setTypeArguments(List<TypeArgumentDecl> typeArguments) {
        this.typeArguments = typeArguments;
        return (T) this;
    }
}
