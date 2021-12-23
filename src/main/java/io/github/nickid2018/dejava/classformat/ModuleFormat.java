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

package io.github.nickid2018.dejava.classformat;

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.DecompileSettings;
import io.github.nickid2018.dejava.api.ClassFileProvider;
import io.github.nickid2018.dejava.api.visitor.ModuleEntryVisitor;
import io.github.nickid2018.dejava.util.Checkers;
import io.github.nickid2018.dejava.util.IntPair;
import io.github.nickid2018.dejava.util.ModifierUtil;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleFormat {

    private final String name;
    private final int accessFlag;
    private final ClassFileProvider provider;

    private final List<String> uses = new ArrayList<>();
    private final Map<String, Integer> requires = new HashMap<>();
    private final Map<String, IntPair<String[]>> opens = new HashMap<>();
    private final Map<String, IntPair<String[]>> exports = new HashMap<>();
    private final Map<String, String[]> provides = new HashMap<>();

    public ModuleFormat(String name, int accessFlag, ClassFileProvider provider) {
        this.name = name;
        this.accessFlag = accessFlag;
        this.provider = provider;
        checkName(name);
    }

    private void checkName(String name) {
        Checkers.errorIfNotMatches(name, ConstantNames.DOT_VALID_NAME,
                "%s is not a valid name.", name);
    }

    public void addUses(String internalName) {
        uses.add(provider.resolveBinaryName(internalName));
    }

    public void addRequires(String module, int accessFlag) {
        checkName(module);
        if (!ModifierUtil.checkFlag(accessFlag, Opcodes.ACC_MANDATED)
                && !(DecompileSettings.noSynthetic && ModifierUtil.checkFlag(accessFlag, Opcodes.ACC_SYNTHETIC)))
            requires.put(module, accessFlag);
    }

    public void addOpens(String packaze, int accessFlag, String[] modules) {
        if (modules != null)
            for (String module : modules)
                checkName(module);
        checkName(packaze);
        if (!ModifierUtil.checkFlag(accessFlag, Opcodes.ACC_MANDATED)
                && !(DecompileSettings.noSynthetic && ModifierUtil.checkFlag(accessFlag, Opcodes.ACC_SYNTHETIC)))
            opens.put(packaze, new IntPair<>(accessFlag, modules));
    }

    public void addExports(String packaze, int accessFlag, String[] modules) {
        checkName(packaze);
        if (modules != null)
            for (String module : modules)
                checkName(module);
        if (!ModifierUtil.checkFlag(accessFlag, Opcodes.ACC_MANDATED)
                && !(DecompileSettings.noSynthetic && ModifierUtil.checkFlag(accessFlag, Opcodes.ACC_SYNTHETIC)))
            exports.put(packaze, new IntPair<>(accessFlag, modules));
    }

    public void addProvides(String service, String[] modules) {
        if (modules != null)
            for (String module : modules)
                checkName(module);
        provides.put(provider.resolveBinaryName(service), modules);
    }

    public void fireVisit(ModuleEntryVisitor visitor) {
        visitor.visitModule(name, accessFlag);
        requires.forEach(visitor::visitRequire);
        exports.forEach((packaze, pair) -> visitor.visitExport(packaze, pair.key(), pair.value()));
        opens.forEach((packaze, pair) -> visitor.visitOpen(packaze, pair.key(), pair.value()));
        uses.forEach(visitor::visitUse);
        provides.forEach(visitor::visitProvide);
    }
}
