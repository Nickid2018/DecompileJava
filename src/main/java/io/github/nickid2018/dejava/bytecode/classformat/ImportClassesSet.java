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

package io.github.nickid2018.dejava.bytecode.classformat;

import io.github.nickid2018.dejava.api.ClassFileProvider;
import io.github.nickid2018.dejava.api.visitor.ImportEntryVisitor;
import io.github.nickid2018.dejava.util.DescriptorUtils;
import io.github.nickid2018.dejava.util.StringUtils;

import java.util.Set;
import java.util.TreeSet;

public class ImportClassesSet {

    private final String nowPackage;
    private final Set<String> names = new TreeSet<>();

    public ImportClassesSet(String nowPackage) {
        this.nowPackage = nowPackage;
    }

    /**
     * Add a class into imports
     *
     * @param name the internal name of the class
     */
    public void addImport(String name, ClassFileProvider provider) {
        // Skip head array type, "LXX;".
        name = DescriptorUtils.getTypeName(name);
        // Check primitive class
        if (DescriptorUtils.isPrimitive(name))
            return;
        boolean innerClass = false;
        if (name.contains("$"))
            // Inner Class?
            innerClass = provider.isInnerClass(name);
        name = provider.resolveBinaryName(name);
        if (name.startsWith("java.lang") && !innerClass)
            return;
        if (provider.isInPackage(nowPackage, name) && !innerClass)
            return;
        names.add(name);
    }

    public boolean isEmpty() {
        return names.isEmpty();
    }

    public void fireVisit(ImportEntryVisitor visitor) {
        names.forEach(visitor::visitImportEntry);
        visitor.visitEnd();
    }

    @Override
    public String toString() {
        return StringUtils.fieldsAsString(getClass(),
                new String[]{"nowPackage", "names"},
                nowPackage, names.toString());
    }
}
