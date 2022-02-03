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

package io.github.nickid2018.dejava.bytecode.statement.constant;

import io.github.nickid2018.dejava.api.visitor.StatementEntryVisitor;
import io.github.nickid2018.dejava.bytecode.statement.Statement;

public class Constant<R> extends Statement {

    public static final Constant<Object> NULL = new Constant<>(ConstantType.NULL);

    private R value;
    private ConstantType type;

    public Constant(R value, ConstantType type) {
        this.value = value;
        this.type = type;
    }

    public Constant(ConstantType type) {
        this(type.getDefaultValue(), type);
    }

    public R getValue() {
        return value;
    }

    public void setValue(R value) {
        this.value = value;
    }

    public ConstantType getType() {
        return type;
    }

    public void setType(ConstantType type) {
        this.type = type;
    }

    @Override
    public void fireVisit(StatementEntryVisitor visitor) {
        visitor.visitConstant(value, type);
    }
}
