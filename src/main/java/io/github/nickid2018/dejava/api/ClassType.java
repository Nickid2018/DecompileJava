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

package io.github.nickid2018.dejava.api;

import org.objectweb.asm.Opcodes;

/**
 * Types of the class.
 */
public enum ClassType {

    /**
     * A plain class
     */
    CLASS(-1),
    /**
     * An interface
     */
    INTERFACE(-1),
    /**
     * An annotation type
     */
    ANNOTATION(-1),
    /**
     * An enum
     */
    ENUM(Opcodes.V1_5),
    /**
     * A record
     */
    RECORD(Opcodes.V14),
    /**
     * A module
     */
    MODULE(Opcodes.V9),
    /**
     * A synthetic class
     */
    SYNTHETIC(-1);

    private final int versionStart;

    ClassType(int versionStart) {
        this.versionStart = versionStart;
    }

    /**
     * Get the type by the access flag.
     *
     * @param accessFlag access flag of the class
     * @return type of the class
     */
    public static ClassType getTypeWithAccessFlag(int accessFlag) {
        if ((accessFlag & Opcodes.ACC_SYNTHETIC) != 0)
            return SYNTHETIC;
        if ((accessFlag & Opcodes.ACC_ANNOTATION) != 0)
            return ANNOTATION;
        if ((accessFlag & Opcodes.ACC_INTERFACE) != 0)
            return INTERFACE;
        if ((accessFlag & Opcodes.ACC_ENUM) != 0)
            return ENUM;
        if ((accessFlag & Opcodes.ACC_RECORD) != 0)
            return RECORD;
        if ((accessFlag & Opcodes.ACC_MODULE) != 0)
            return MODULE;
        return CLASS;
    }

    /**
     * Get the version that the type was added to Java
     *
     * @return a version
     */
    public int getVersionStart() {
        return versionStart;
    }
}
