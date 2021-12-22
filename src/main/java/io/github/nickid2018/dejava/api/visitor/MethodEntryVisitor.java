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

import io.github.nickid2018.dejava.api.IModifier;
import io.github.nickid2018.dejava.api.Modifiers;
import io.github.nickid2018.dejava.util.Checkers;
import io.github.nickid2018.dejava.util.ModifierUtil;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

public abstract class MethodEntryVisitor {

    public static final int ACC_PUBLIC = Opcodes.ACC_PUBLIC;
    public static final int ACC_PRIVATE = Opcodes.ACC_PRIVATE;
    public static final int ACC_PROTECTED = Opcodes.ACC_PROTECTED;
    public static final int ACC_STATIC = Opcodes.ACC_STATIC;
    public static final int ACC_FINAL = Opcodes.ACC_FINAL;
    public static final int ACC_SYNCHRONIZED = Opcodes.ACC_SYNCHRONIZED;
    public static final int ACC_BRIDGE = Opcodes.ACC_BRIDGE;
    public static final int ACC_VARARGS = Opcodes.ACC_VARARGS;
    public static final int ACC_NATIVE = Opcodes.ACC_NATIVE;
    public static final int ACC_ABSTRACT = Opcodes.ACC_ABSTRACT;
    public static final int ACC_STRICT = Opcodes.ACC_STRICT;
    public static final int ACC_SYNTHETIC = Opcodes.ACC_SYNTHETIC;

    public static List<IModifier> parseMethodEntryAccessFlag(int accessFlag) {
        List<IModifier> modifiers = new ArrayList<>();
        boolean isPrivate;
        if (ModifierUtil.checkFlag(accessFlag, ACC_PUBLIC))
            modifiers.add(Modifiers.PUBLIC);
        if (ModifierUtil.checkFlag(accessFlag, ACC_PROTECTED))
            modifiers.add(Modifiers.PROTECTED);
        if (isPrivate = ModifierUtil.checkFlag(accessFlag, ACC_PRIVATE))
            modifiers.add(Modifiers.PRIVATE);
        Checkers.errorIfTrue(modifiers.size() > 1,
                "A method must have at most one public/protected/private");
        boolean isStatic, isFinal, isSynchronized, isNative, isAbstract, isStrictFP;
        if (isStatic = ModifierUtil.checkFlag(accessFlag, ACC_STATIC))
            modifiers.add(Modifiers.STATIC);
        if (isFinal = ModifierUtil.checkFlag(accessFlag, ACC_FINAL))
            modifiers.add(Modifiers.FINAL);
        if (isSynchronized = ModifierUtil.checkFlag(accessFlag, ACC_SYNCHRONIZED))
            modifiers.add(Modifiers.SYNCHRONIZED);
        if (isNative = ModifierUtil.checkFlag(accessFlag, ACC_NATIVE))
            modifiers.add(Modifiers.NATIVE);
        if (isAbstract = ModifierUtil.checkFlag(accessFlag, ACC_ABSTRACT))
            modifiers.add(Modifiers.ABSTRACT);
        if (isStrictFP = ModifierUtil.checkFlag(accessFlag, ACC_STRICT))
            modifiers.add(Modifiers.STRICTFP);
        Checkers.errorIfTrue(isPrivate && isAbstract, "A method can't be both private and abstract");
        Checkers.errorIfTrue(isStatic && isAbstract, "A method can't be both static and abstract");
        Checkers.errorIfTrue(isFinal && isAbstract, "A method can't be both final and abstract");
        Checkers.errorIfTrue(isSynchronized && isAbstract, "A method can't be both synchronized and abstract");
        Checkers.errorIfTrue(isNative && isAbstract, "A method can't be both native and abstract");
        Checkers.errorIfTrue(isStrictFP && isAbstract, "A method can't be both strict-fp and abstract");
        return modifiers;
    }

    /**
     * Visit method declaration.
     * @param accessFlag access flag of the method
     *                   <ul>
     *                      <li>{@link #ACC_PUBLIC} - The method is public.</li>
     *                      <li>{@link #ACC_PRIVATE} - The method is private.</li>
     *                      <li>{@link #ACC_PROTECTED} - The method is protected.</li>
     *                      <li>{@link #ACC_STATIC} - The method is static.</li>
     *                      <li>{@link #ACC_FINAL} - The method is final.</li>
     *                      <li>{@link #ACC_SYNCHRONIZED} - The method is synchronized.</li>
     *                      <li>{@link #ACC_BRIDGE} - The method is a bridge method.</li>
     *                      <li>{@link #ACC_VARARGS} - The method is var-arguments.</li>
     *                      <li>{@link #ACC_NATIVE} - The method is native.</li>
     *                      <li>{@link #ACC_ABSTRACT} - The method is abstract.</li>
     *                      <li>{@link #ACC_STRICT} - The method is strict-fp.</li>
     *                      <li>{@link #ACC_SYNTHETIC} - The method is synthetic.
     *                         If {@link io.github.nickid2018.dejava.DecompileSettings#noSynthetic} is true,
     *                         the method will not receive this except it's a lambda target.</li>
     *                   </ul>
     * @param name name of the method
     * @param desc descriptor of the method
     * @param exceptions exceptions the method may throw
     */
    public void visitMethod(int accessFlag, String name, String desc, String... exceptions) {
    }

    /**
     * Visit when the method doesn't have a body, such as abstract and native methods.
     */
    public void visitNoMethodBody(){
    }

    /**
     * Visit when the operations are over.
     */
    public void visitEnd(){
    }
}
