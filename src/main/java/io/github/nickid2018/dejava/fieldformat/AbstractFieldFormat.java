package io.github.nickid2018.dejava.fieldformat;

import io.github.nickid2018.dejava.Checkers;
import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.WarnList;
import io.github.nickid2018.dejava.classformat.AbstractClassFormat;
import org.objectweb.asm.Opcodes;

import static io.github.nickid2018.dejava.ConstantNames.*;

public abstract class AbstractFieldFormat {

    private final AbstractClassFormat classFormat;
    private final String name;
    private final String fieldType;
    private final int accessFlag;
    private String initialValue;

    public AbstractFieldFormat(AbstractClassFormat classFormat, String name, String descriptor,
                               int accessFlag, Object initialValue)
            throws DecompileException {
        this.classFormat = classFormat;
        this.accessFlag = accessFlag;
        this.name = name;
        Checkers.checkIfTrue(KEYWORDS_ALL_RESTRICTED.contains(name),
                "Invalid field name: %s is a keyword or reserved word", name);
        Checkers.checkIfFalse(VALID_NAME.matcher(name).matches(),
                "Invalid field name: %s has illegal characters", name);
        if (!BEST_NAMING.matcher(name).matches())
            WarnList.warn("%s isn't a good field name: have non-ASCII character");
        classFormat.getImports().addImport(descriptor);
        fieldType = descriptor;
        if ((accessFlag & Opcodes.ACC_STATIC) != 0 && initialValue != null) {
            // Static field should have an initial value (maybe null, but we ignore it)
            // The value should be Integer, Float, Long, Double or String
            this.initialValue = switch (initialValue) {
                case Integer i -> i + "";
                case Float f -> f + "f";
                case Long l -> l + "l";
                case Double d -> d + "";
                case String s -> "\"" + s + "\"";
                default -> "";
            };
        }
    }

    public String getName() {
        return name;
    }

    public String getFieldType() {
        return fieldType;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public AbstractClassFormat getClassFormat() {
        return classFormat;
    }

    public String getInitialValue() {
        return initialValue;
    }
}
