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
import io.github.nickid2018.dejava.api.FieldType;
import io.github.nickid2018.dejava.api.visitor.FieldEntryVisitor;
import io.github.nickid2018.dejava.bytecode.classformat.AbstractClassFormat;
import io.github.nickid2018.dejava.bytecode.statement.constant.Constant;

public class PlainFieldFormat extends AbstractFieldFormat {

    public PlainFieldFormat(AbstractClassFormat classFormat, String name, String descriptor, int accessFlag, Object initialValue)
            throws DecompileException {
        super(classFormat, name, descriptor, accessFlag, initialValue);
    }

    @Override
    public void fireVisit(FieldEntryVisitor visitor) {
        visitor.visitField(accessFlag, fieldType, name);
        if (initialValue == null)
            visitor.visitNoInitialValue();
        else {
            if (initialValue instanceof Constant<?> constant)
                visitor.visitInitialValue(constant);
        }
        visitor.visitEnd();
    }

    @Override
    public FieldType getType() {
        return FieldType.PLAIN;
    }
}
