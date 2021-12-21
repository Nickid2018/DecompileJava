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

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.ast.FormatControl;

public final class Modifiers {
    public static final int CLASS_MODIFIER = 0b0001;
    public static final int FIELD_MODIFIER = 0b0010;
    public static final int METHOD_MODIFIER = 0b0100;
    public static final int CONSTRUCTOR_MODIFIER = 0b1000;
    public static final Modifier PUBLIC = new Modifier(ConstantNames.PUBLIC, 0);
    public static final Modifier PRIVATE = new Modifier(ConstantNames.PRIVATE, 0);
    public static final Modifier PROTECTED = new Modifier(ConstantNames.PROTECTED, 0);
    public static final Modifier DEFAULT = new Modifier(ConstantNames.DEFAULT, 1);
    public static final Modifier ABSTRACT = new Modifier(ConstantNames.ABSTRACT, 1);
    public static final Modifier SEALED = new Modifier(ConstantNames.SEALED, 1);
    public static final Modifier FINAL = new Modifier(ConstantNames.FINAL, 2);
    public static final Modifier STATIC = new Modifier(ConstantNames.STATIC, 3);
    public static final Modifier SYNCHRONIZED = new Modifier(ConstantNames.SYNCHRONIZED, 4);
    public static final Modifier VOLATILE = new Modifier(ConstantNames.VOLATILE, 5);
    public static final Modifier TRANSIENT = new Modifier(ConstantNames.TRANSIENT, 5);
    public static final Modifier STRICTFP = new Modifier(ConstantNames.STRICTFP, 6);
    public static final Modifier NATIVE = new Modifier(ConstantNames.NATIVE, 6);
    public static final Modifier SYNTHETIC = new Modifier("/*synthetic*/", 4);

    /**
     * Shared non-annotation modifiers
     */
    static final class Modifier implements IModifier {
        private final String name;
        private final int scope;
        private final int priority;

        protected Modifier(String name, int priority) {
            this.name = name;
            this.scope = 0; //TODO: add scope
            this.priority = priority;
        }

        @Override
        public boolean isAnnotation() {
            return false;
        }

        @Override
        public String getName() {
            return name;
        }

        public int getScope() {
            return scope;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(IModifier o) {
            if (o.isAnnotation()) return 999;
            if (o instanceof Modifier mod) return this.priority - mod.priority;
            return 0;
        }

        @Override
        public String toSource(FormatControl fc) {
            return this.getName();
        }
    }

}
