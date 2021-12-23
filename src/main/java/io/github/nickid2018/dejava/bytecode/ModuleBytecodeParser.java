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

import io.github.nickid2018.dejava.classformat.ModuleFormat;
import org.objectweb.asm.ModuleVisitor;
import org.objectweb.asm.Opcodes;

public class ModuleBytecodeParser extends ModuleVisitor implements Opcodes {

    private final ModuleFormat format;

    public ModuleBytecodeParser(ModuleFormat format) {
        super(ASM9);
        this.format = format;
    }

    @Override
    public void visitMainClass(String mainClass) {
        // Ignore
    }

    @Override
    public void visitPackage(String packaze) {
        // Ignore
    }

    @Override
    public void visitRequire(String module, int access, String version) {
        format.addRequires(module, access);
    }

    @Override
    public void visitExport(String packaze, int access, String... modules) {
        format.addExports(packaze, access, modules);
    }

    @Override
    public void visitOpen(String packaze, int access, String... modules) {
        format.addOpens(packaze, access, modules);
    }

    @Override
    public void visitUse(String service) {
        format.addUses(service);
    }

    @Override
    public void visitProvide(String service, String... providers) {
        format.addProvides(service, providers);
    }

    @Override
    public void visitEnd() {
        // Ignore
    }
}
