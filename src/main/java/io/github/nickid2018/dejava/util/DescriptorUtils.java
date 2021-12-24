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

package io.github.nickid2018.dejava.util;

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.ast.Typename;

public class DescriptorUtils {

    public static boolean isPrimitive(String typeName) {
        return switch(typeName) {
            case "B", "C", "D", "F", "I", "J", "S", "Z", "V" -> true;
            default -> false;
        };
    }

    public static String getTypeName(String qualifiedName) {
        qualifiedName = qualifiedName.substring(Math.max(0, 1 + qualifiedName.lastIndexOf('[')));
        return qualifiedName.startsWith("L") && qualifiedName.endsWith(";") ?
                qualifiedName.substring(1, qualifiedName.length() - 1) : qualifiedName;
    }

    public static enum DescriptorType {
        B(ConstantNames.BYTE), 
        C(ConstantNames.CHAR), 
        D(ConstantNames.DOUBLE), 
        F(ConstantNames.FLOAT), 
        I(ConstantNames.INT),
        J(ConstantNames.LONG),
        S(ConstantNames.SHORT),
        Z(ConstantNames.BOOLEAN);

        public final String type;
        DescriptorType(String type) {
            this.type = type;
        }

        public static String keyOf(String s) {
            for (DescriptorType t : values()) {
                if (t.type.equals(s)) return t.name();
            }
            return null;
        }
    }
    
    /**
     * Parse a field descriptor to corresponding type name.
     * 
     * @param descriptor The field descriptor.
     * @throws IllegalArgumentException If the descriptor is invalid.
     */
    public static Typename toTypename(String descriptor) {

        int dims = 0;
        boolean dimDone = false;
        for(int i = 0; i < descriptor.length(); i++) {
            char c = descriptor.charAt(i);
            if(c == '[') {
                if (!dimDone) {
                    dims++;
                    continue;
                } else throw new IllegalArgumentException("Invalid descriptor: " + descriptor);
            } else {
                dimDone = true;
            }

            if(c == 'L') {
                StringBuilder sb = new StringBuilder();
                for(; i < descriptor.length(); i++) {
                    if(descriptor.charAt(i) == ';') {
                        break;
                    }
                    sb.append(descriptor.charAt(i));
                }
                sb.append(";");

                // TODO make typename full qualified name aware
                var tn = getTypeName(sb.toString()).split("/");
                var name = tn[tn.length - 1];
                return new Typename(name).setArrayDim(dims);
            }

            var desc = DescriptorType.valueOf(Character.toString(c));
            if (desc != null) {
                return new Typename(desc.type).setArrayDim(dims);
            }
        }
        throw new IllegalArgumentException("Invalid descriptor: " + descriptor);
    }
}
