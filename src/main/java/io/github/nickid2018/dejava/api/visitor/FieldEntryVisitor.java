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

import io.github.nickid2018.dejava.DecompileSettings;
import io.github.nickid2018.dejava.api.IModifier;
import io.github.nickid2018.dejava.api.Modifiers;
import io.github.nickid2018.dejava.util.Checkers;
import io.github.nickid2018.dejava.util.ModifierUtil;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor for field. The order is:
 * <code>(visitAnnotation)(visitSignature)[visitNoInitialValue|visitInitialValue|visitInitialWithStatement]visitEnd</code>
 */
public abstract class FieldEntryVisitor {

    // Access flags using in field declaration
    public static final int ACC_PUBLIC = Opcodes.ACC_PUBLIC;
    public static final int ACC_PROTECTED = Opcodes.ACC_PROTECTED;
    public static final int ACC_PRIVATE = Opcodes.ACC_PRIVATE;
    public static final int ACC_STATIC = Opcodes.ACC_STATIC;
    public static final int ACC_FINAL = Opcodes.ACC_FINAL;
    public static final int ACC_SYNTHETIC = Opcodes.ACC_SYNTHETIC;
    public static final int ACC_ENUM = Opcodes.ACC_ENUM;
    public static final int ACC_VOLATILE = Opcodes.ACC_VOLATILE;
    public static final int ACC_TRANSIENT = Opcodes.ACC_TRANSIENT;

    public static List<IModifier> parseClassEntryAccessFlag(int accessFlag) {
        List<IModifier> modifiers = new ArrayList<>();
        if (ModifierUtil.checkFlag(accessFlag, ACC_PUBLIC))
            modifiers.add(Modifiers.PUBLIC);
        if (ModifierUtil.checkFlag(accessFlag, ACC_PROTECTED))
            modifiers.add(Modifiers.PROTECTED);
        if (ModifierUtil.checkFlag(accessFlag, ACC_PRIVATE))
            modifiers.add(Modifiers.PRIVATE);
        Checkers.errorIfTrue(modifiers.size() > 1,
                "A field must have at most one public/protected/private");
        boolean isStatic, isFinal, isVolatile;
        if (isStatic = ModifierUtil.checkFlag(accessFlag, ACC_STATIC))
            modifiers.add(Modifiers.STATIC);
        if (isFinal = ModifierUtil.checkFlag(accessFlag, ACC_FINAL))
            modifiers.add(Modifiers.FINAL);
        if (isVolatile = ModifierUtil.checkFlag(accessFlag, ACC_VOLATILE))
            modifiers.add(Modifiers.VOLATILE);
        if (ModifierUtil.checkFlag(accessFlag, ACC_TRANSIENT))
            modifiers.add(Modifiers.TRANSIENT);
        Checkers.errorIfTrue(isFinal && isVolatile, "A field can't be both final and volatile");
        Checkers.errorIfTrue(ModifierUtil.checkFlag(accessFlag, ACC_ENUM) && !(isStatic && isFinal),
                "An enum field should be static and final");
        return modifiers;
    }


    /**
     * Visit field declaration.
     *
     * @param accessFlag access flag of the field, may be these as follows:
     *                   <ul>
     *                      <li>{@link #ACC_PUBLIC} - The field is public.</li>
     *                      <li>{@link #ACC_PROTECTED} - The field is protected.</li>
     *                      <li>{@link #ACC_PRIVATE} - The field is private.</li>
     *                      <li>{@link #ACC_STATIC} - The field is static.</li>
     *                      <li>{@link #ACC_FINAL} - The field is final.</li>
     *                      <li>{@link #ACC_SYNTHETIC} - The class is synthetic.
     *                                   If {@link DecompileSettings#noSynthetic} is true, the method can't receive this. (such as: enum $VALUES)</li>
     *                      <li>{@link #ACC_ENUM} - The field is an enum field.</li>
     *                      <li>{@link #ACC_TRANSIENT} - The field is transient.</li>
     *                      <li>{@link #ACC_VOLATILE} - The field is volatile.</li>
     *                   </ul>
     *                   If the field is a record component, the access flag is -1.
     * @param type       type of the class (not including type parameters!)
     * @param name       name of the field
     */
    public void visitField(int accessFlag, String type, String name) {
    }

    /**
     * Visit when the field has annotations.
     *
     * @param name the class name of the annotation
     * @return a annotation visitor
     */
    public AnnotationEntryVisitor visitAnnotation(String name) {
        return null;
    }

    /**
     * Visit when the field have templates.
     *
     * @return a signature information visitor
     */
    public SignatureVisitor visitSignature() {
        return null;
    }

    /**
     * Visit when the field doesn't have an initial value can be parsed in a line.
     */
    public void visitNoInitialValue() {
    }

    /**
     * Visit when the field have an initial value with a constant.
     *
     * @param initialValue the initial value of the field
     */
    public void visitInitialValue(String initialValue) {
    }

    /**
     * Visit when the field is assigned with a statement(including lambda).
     *
     * @return a statement visitor
     */
    public StatementEntryVisitor visitInitialWithStatement() {
        return null;
    }

    /**
     * Visit when the field has been read.
     */
    public void visitEnd() {
    }
}
