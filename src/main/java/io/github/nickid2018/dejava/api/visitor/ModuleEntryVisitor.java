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

package io.github.nickid2018.dejava.api.visitor;

import io.github.nickid2018.dejava.api.ClassType;
import io.github.nickid2018.dejava.api.FieldType;
import io.github.nickid2018.dejava.api.MethodType;
import org.objectweb.asm.signature.SignatureVisitor;

/**
 * Visitor for modules or 'module-info.class'.
 */
public abstract class ModuleEntryVisitor extends ClassEntryVisitor {

    // --- Can't be overridden

    @Override
    public final void visitPackage(String packageName) {
    }

    @Override
    public final ImportEntryVisitor visitImports() {
        return null;
    }

    @Override
    public final void visitClass(int accessFlag, String name, String superClass, String... interfaces) {
    }

    @Override
    public final SignatureVisitor visitSignature() {
        return null;
    }

    @Override
    public final FieldEntryVisitor visitFieldEntry(FieldType fieldType) {
        return null;
    }

    @Override
    public final MethodEntryVisitor visitMethodEntry(MethodType fieldType) {
        return null;
    }

    @Override
    public final ClassEntryVisitor visitInnerClassEntry(ClassType type) {
        return null;
    }

    // --- Module methods
}
