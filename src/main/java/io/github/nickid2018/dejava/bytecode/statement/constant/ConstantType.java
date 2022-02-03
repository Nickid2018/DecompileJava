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

public enum ConstantType {

    BOOLEAN("Z", false, ""),
    INT("I", 0, ""),
    LONG("L", 0L, "L"),
    FLOAT("F", 0.0f, "F"),
    DOUBLE("D", 0.0, ""),
    STRING("Ljava/lang/String;", "", ""),
    NULL(null, null, "");

    public final String descriptor;
    public final Object defaultValue;
    public final String suffix;

    ConstantType(String descriptor, Object defaultValue, String suffix) {
        this.descriptor = descriptor;
        this.defaultValue = defaultValue;
        this.suffix = suffix;
    }

    @SuppressWarnings("unchecked")
    public <T> T getDefaultValue() {
        return (T) defaultValue;
    }
}
