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

package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.api.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class FieldDeclTest {
    @Test
    void test() {

        var result = new FieldDecl(Typename.of("int"),
                List.of(new VarDecl("field0", null, true))
        ).addModifiers(Modifiers.PUBLIC).toSource();
        assertTrue(result.contains("field0"));
        assertTrue(result.contains("[]"));
        System.out.println(result);
    }
    // public int field0[];
}
