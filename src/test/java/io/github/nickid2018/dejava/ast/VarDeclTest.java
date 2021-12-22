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

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class VarDeclTest {
    @Test
    void testContainsName() {
        var result = new VarDecl("field0").toSource();
        assertTrue(result.contains("field0"));
        assertFalse(result.contains("="));
        System.out.println(result);
    }

    @Test
    void testInitializer() {
        var result = new VarDecl("field0", new VarInitializer()).toSource();
        assertTrue(result.contains("field0"));
        assertTrue(result.contains("="));
        System.out.println(result);
    }

    @Test
    void testDims() {
        var result = new VarDecl("field0", null, true).toSource();
        assertTrue(result.contains("field0"));
        assertTrue(result.contains("[]"));
        System.out.println(result);
    }
}
