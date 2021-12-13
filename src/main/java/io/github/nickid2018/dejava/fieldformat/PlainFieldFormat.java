package io.github.nickid2018.dejava.fieldformat;

import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.classformat.AbstractClassFormat;

public class PlainFieldFormat extends AbstractFieldFormat {

    public PlainFieldFormat(AbstractClassFormat classFormat, String name, String descriptor, int accessFlag, Object initialValue)
            throws DecompileException {
        super(classFormat, name, descriptor, accessFlag, initialValue);
    }
}
