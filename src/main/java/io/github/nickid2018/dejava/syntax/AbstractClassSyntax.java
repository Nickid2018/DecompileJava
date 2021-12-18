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

package io.github.nickid2018.dejava.syntax;

import io.github.nickid2018.dejava.api.ClassType;
import io.github.nickid2018.dejava.api.visitor.ClassEntryVisitor;
import io.github.nickid2018.dejava.api.visitor.FieldEntryVisitor;
import io.github.nickid2018.dejava.api.visitor.ImportEntryVisitor;
import io.github.nickid2018.dejava.util.MarkWriter;
import org.objectweb.asm.signature.SignatureVisitor;

public abstract class AbstractClassSyntax implements ClassEntryVisitor {

    private MarkWriter output;

    public AbstractClassSyntax(MarkWriter output) {
        this.output = output;
    }

    @Override
    public void visitPackage(String packageName) {
        // Visit Package
        output.newLine("package %s;", packageName).newLine();
    }

    @Override
    public ImportEntryVisitor visitImports() {
        return new ImportSyntax(output);
    }

    @Override
    public SignatureVisitor visitSignature() {
        return null;
    }

    @Override
    public void visitClass(int accessFlag, ClassType type, String name, String superClass, String[] interfaces) {
        // Import entry position
        output.mark("import").newLine();
    }

    @Override
    public FieldEntryVisitor visitFieldEntry(int accessFlag, String type, String name) {
        return null;
    }
}
