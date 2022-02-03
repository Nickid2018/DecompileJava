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

package io.github.nickid2018.dejava.bytecode.fieldformat;

import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.WarnList;
import io.github.nickid2018.dejava.api.FieldType;
import io.github.nickid2018.dejava.api.visitor.FieldEntryVisitor;
import io.github.nickid2018.dejava.bytecode.classformat.AbstractClassFormat;
import io.github.nickid2018.dejava.bytecode.statement.Statement;
import io.github.nickid2018.dejava.bytecode.statement.constant.Constant;
import io.github.nickid2018.dejava.bytecode.statement.constant.ConstantType;
import io.github.nickid2018.dejava.util.Checkers;
import io.github.nickid2018.dejava.util.ModifierUtil;
import org.objectweb.asm.Opcodes;

import static io.github.nickid2018.dejava.ConstantNames.*;

public abstract class AbstractFieldFormat {

    protected final AbstractClassFormat classFormat;
    protected final String name;
    protected final String fieldType;
    protected final int accessFlag;
    protected Statement initialValue;
    protected boolean isSynthetic;

    public AbstractFieldFormat(AbstractClassFormat classFormat, String name, String descriptor,
                               int accessFlag, Object initialValue)
            throws DecompileException {
        this.classFormat = classFormat;
        this.accessFlag = accessFlag;
        this.name = name;
        isSynthetic = ModifierUtil.checkFlag(accessFlag, Opcodes.ACC_SYNTHETIC);
        Checkers.errorIfTrue(KEYWORDS_ALL_RESTRICTED.contains(name),
                "Invalid field name: %s is a keyword or reserved word", name);
        Checkers.errorIfNotMatches(name, VALID_NAME,
                "Invalid field name: %s has illegal characters", name);
        if (!Checkers.match(name, BEST_NAMING))
            WarnList.warn(classFormat.getClassName(), "%s isn't a good field name: have non-ASCII character", name);
        classFormat.getImports().addImport(descriptor, classFormat.getFileProvider());
        fieldType = descriptor;
        if (ModifierUtil.checkFlag(accessFlag, Opcodes.ACC_STATIC)) {
            // Static field should have an initial value
            // The value should be null, Integer, Float, Long, Double or String
            this.initialValue = switch (initialValue) {
                case null -> Constant.NULL;
                case String str -> new Constant<>(str, ConstantType.STRING);
                case Double d -> new Constant<>(d, ConstantType.DOUBLE);
                case Long l -> new Constant<>(l, ConstantType.LONG);
                case Float f -> new Constant<>(f, ConstantType.FLOAT);
                case Integer i -> {
                    if (descriptor.equals("Z"))
                        yield new Constant<>(i != 0, ConstantType.BOOLEAN);
                    else
                        yield new Constant<>(i, ConstantType.INT);
                }
                default -> throw new DecompileException("Unexpected field value: " + initialValue);
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

    public Statement getInitialValue() {
        return initialValue;
    }

    public boolean isSynthetic() {
        return isSynthetic;
    }

    public void setSynthetic(boolean synthetic) {
        isSynthetic = synthetic;
    }

    public abstract void fireVisit(FieldEntryVisitor visitor);

    public abstract FieldType getType();
}
