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

import io.github.nickid2018.dejava.api.*;
import io.github.nickid2018.dejava.util.ModifierUtil;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor for modules or 'module-info.class'.
 */
public abstract class ModuleEntryVisitor extends ClassEntryVisitor {

    public static final int ACC_OPEN = Opcodes.ACC_OPEN;
    public static final int ACC_MANDATED = Opcodes.ACC_MANDATED; // Will not appear in this API
    public static final int ACC_STATIC_PHASE = Opcodes.ACC_STATIC_PHASE;
    public static final int ACC_TRANSITIVE = Opcodes.ACC_TRANSITIVE;

    public static List<IModifier> parseModuleEntryAccessFlag(int accessFlag) {
        List<IModifier> modifiers = new ArrayList<>();
        if (ModifierUtil.checkFlag(accessFlag, ACC_OPEN))
            modifiers.add(Modifiers.OPEN);
        return modifiers;
    }

    public static List<IModifier> parseRequireAccessFlag(int accessFlag) {
        List<IModifier> modifiers = new ArrayList<>();
        if (ModifierUtil.checkFlag(accessFlag, ACC_STATIC_PHASE))
            modifiers.add(Modifiers.STATIC);
        if (ModifierUtil.checkFlag(accessFlag, ACC_TRANSITIVE))
            modifiers.add(Modifiers.TRANSITIVE);
        return modifiers;
    }

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

    /**
     * Visit module declaration.
     * @param moduleName name of the module
     * @param accessFlag access flag of the module
     *                   <ul>
     *                      <li>{@link #ACC_OPEN} - If the module is open.</li>
     *                   </ul>
     */
    public void visitModule(String moduleName, int accessFlag) {
    }

    /**
     * Visit requirement.
     * @param require module the module requires
     * @param accessFlag access flag of the entry
     *                   <ul>
     *                      <li>{@link #ACC_STATIC_PHASE} - If this dependence is mandatory in the static phase.</li>
     *                      <li>{@link #ACC_TRANSITIVE} </li>
     *                   </ul>
     */
    public void visitRequire(String require, int accessFlag) {
    }

    /**
     * Visit export entries.
     * @param packaze package to export
     * @param accessFlag access flag (CAN BE IGNORED)
     * @param modules modules the package export to
     */
    public void visitExport(String packaze, int accessFlag, String... modules){
    }

    /**
     * Visit open entries.
     * @param packaze package to open
     * @param accessFlag access flag (CAN BE IGNORED)
     * @param modules modules the package opens to
     */
    public void visitOpen(String packaze, int accessFlag, String... modules){
    }

    /**
     * Visit use entries.
     * @param use binary name of the class
     */
    public void visitUse(String use) {
    }

    /**
     * Visit providers.
     * @param service binary name of the service class
     * @param provide modules the service provides
     */
    public void visitProvide(String service, String... provide){
    }
}
