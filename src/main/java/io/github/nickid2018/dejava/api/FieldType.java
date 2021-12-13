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

    public int getVersionStart() {
        return versionStart;
    }

    public static FieldType getTypeWithAccessFlag(int accessFlag) {
        if ((accessFlag & Opcodes.ACC_SYNTHETIC) != 0)
            return SYNTHETIC;
        return (accessFlag & Opcodes.ACC_ENUM) != 0 ? ENUM : PLAIN;
    }
}
