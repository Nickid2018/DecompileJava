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

public enum FieldType {

    PLAIN(-1),
    ENUM(Opcodes.V1_5),
    RECORD(Opcodes.V14), // Need class formatter
    SYNTHETIC(-1);

    private final int versionStart;

    FieldType(int versionStart) {
        this.versionStart = versionStart;
    }

    public static FieldType getTypeWithAccessFlag(int accessFlag) {
        if ((accessFlag & Opcodes.ACC_SYNTHETIC) != 0)
            return SYNTHETIC;
        return (accessFlag & Opcodes.ACC_ENUM) != 0 ? ENUM : PLAIN;
    }

    public int getVersionStart() {
        return versionStart;
    }
}
