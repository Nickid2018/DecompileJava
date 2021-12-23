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

package io.github.nickid2018.dejava.fieldformat;

import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.WarnList;
import io.github.nickid2018.dejava.api.visitor.FieldEntryVisitor;
import io.github.nickid2018.dejava.classformat.AbstractClassFormat;
import io.github.nickid2018.dejava.util.Checkers;
import org.objectweb.asm.Opcodes;

import static io.github.nickid2018.dejava.ConstantNames.*;

public abstract class AbstractFieldFormat {

    private final AbstractClassFormat classFormat;
    private final String name;
    private final String fieldType;
    private final int accessFlag;
    private String initialValue;
    private boolean isSynthetic;

    public AbstractFieldFormat(AbstractClassFormat classFormat, String name, String descriptor,
                               int accessFlag, Object initialValue)
            throws DecompileException {
        this.classFormat = classFormat;
        this.accessFlag = accessFlag;
        this.name = name;
        Checkers.errorIfTrue(KEYWORDS_ALL_RESTRICTED.contains(name),
                "Invalid field name: %s is a keyword or reserved word", name);
        Checkers.errorIfNotMatches(name, VALID_NAME,
                "Invalid field name: %s has illegal characters", name);
        if (!Checkers.match(name, BEST_NAMING))
            WarnList.warn(classFormat.getClassName(), "%s isn't a good field name: have non-ASCII character", name);
        classFormat.getImports().addImport(descriptor, classFormat.getFileProvider());
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
                default -> throw new DecompileException("Impossible error");
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

    public boolean isSynthetic() {
        return isSynthetic;
    }

    public void setSynthetic(boolean synthetic) {
        isSynthetic = synthetic;
    }

    public void fireVisit(FieldEntryVisitor visitor) {

    }
}
