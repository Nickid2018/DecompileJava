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

package io.github.nickid2018.dejava.bytecode;

import io.github.nickid2018.dejava.WarnList;
import io.github.nickid2018.dejava.api.ClassFileProvider;
import io.github.nickid2018.dejava.bytecode.parser.ClassBytecodeParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

@DisplayName("AST Parse Test")
public class ClassASTParseTest {

    @Test
    @DisplayName("Class Parse Test")
    public void testClassParse() throws IOException {
        ClassBytecodeParser parser = new ClassBytecodeParser("test", new ClassFileProvider() {
            @Override
            public boolean isInnerClass(String name) {
                // Ignore
                return false;
            }

            @Override
            public boolean isInPackage(String packageName, String binaryName) {
                // Ignore
                return false;
            }

            @Override
            public String resolveBinaryName(String internalName) {
                return internalName.replace('/', '.');
            }

            @Override
            public InputStream getClassFile(String internalName) throws IOException {
                return getClass().getResourceAsStream("/io/github/nickid2018/dejava/bytecode/ClassBytecodeParser.class");
            }
        });
        System.out.println(parser.analyze());
        System.out.println(WarnList.getWarns());
    }
}
