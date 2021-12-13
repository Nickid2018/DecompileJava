package io.github.nickid2018.dejava.api;

import org.objectweb.asm.Opcodes;

public enum ClassType {

    CLASS(-1),
    INTERFACE(-1),
    ANNOTATION(-1),
    ENUM(Opcodes.V1_5),
    RECORD(Opcodes.V14),
    SYNTHETIC(-1);

    private final int versionStart;

    ClassType(int versionStart) {
        this.versionStart = versionStart;
    }

    public int getVersionStart() {
        return versionStart;
    }

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
        return CLASS;
    }
}
