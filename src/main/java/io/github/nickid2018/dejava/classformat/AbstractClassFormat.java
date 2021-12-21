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

import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.WarnList;
import io.github.nickid2018.dejava.api.ClassFileProvider;
import io.github.nickid2018.dejava.api.visitor.ClassEntryVisitor;
import io.github.nickid2018.dejava.api.visitor.ImportEntryVisitor;
import io.github.nickid2018.dejava.fieldformat.AbstractFieldFormat;
import io.github.nickid2018.dejava.methodformat.AbstractMethodFormat;
import io.github.nickid2018.dejava.util.Checkers;
import io.github.nickid2018.dejava.util.StringUtils;
import org.objectweb.asm.signature.SignatureVisitor;

import java.util.*;

import static io.github.nickid2018.dejava.ConstantNames.*;

public abstract class AbstractClassFormat {

    public static final List<String> INVALID_CLASS_NAMES;

    static {
        List<String> keywords = new ArrayList<>(KEYWORDS_ALL_RESTRICTED);
        keywords.addAll(List.of(
                RECORD, VAR, SEALED, PERMITS
        ));
        INVALID_CLASS_NAMES = Collections.unmodifiableList(keywords);
    }

    protected final ClassFileProvider fileProvider;

    protected final String className;
    protected final String packageName;
    protected final int accessFlag;

    protected final ImportClassesSet imports;
    protected SignatureInfos signature;
    protected String superClass;
    protected String[] interfaces;

    protected Map<String, AbstractFieldFormat> fieldFormats = new HashMap<>(); // Key - name
    protected Map<String, AbstractMethodFormat> methodFormats = new HashMap<>(); // Key - name and desc

    /**
     * Create an instance with certain internal name.
     *
     * @param name       Internal Name
     * @param accessFlag Access flag for the class
     * @param superClass Super class for the class
     * @param interfaces The implemented classes
     * @param provider   Provider for classes
     * @throws DecompileException throws if the name of the class is illegal
     */
    public AbstractClassFormat(String name, int accessFlag, String superClass, final String[] interfaces, ClassFileProvider provider)
            throws DecompileException {
        this.accessFlag = accessFlag;
        this.superClass = superClass;
        this.interfaces = interfaces;
        fileProvider = provider;
        int split = Math.max(-1, name.lastIndexOf('/'));
        className = name.substring(split + 1);
        packageName = split > 0 ? name.substring(0, split).replace('/', '.') : null;
        // --- Verify class name
        Checkers.errorIfTrue(className.isEmpty(), "Invalid class name: empty");
        Checkers.errorIfTrue(INVALID_CLASS_NAMES.contains(className),
                "Invalid class name: %s is a keyword or reserved word", className);
        Checkers.errorIfNotMatches(className, VALID_NAME,
                "Invalid class name: %s has illegal characters", className);
        if (!BEST_NAMING.matcher(className).matches())
            WarnList.warn(className, "%s isn't a good class name: have non-ASCII character", className);
        imports = new ImportClassesSet(packageName);
        if (superClass != null)
            imports.addImport(superClass, provider);
        for (String clazz : interfaces)
            imports.addImport(clazz, provider);
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public ImportClassesSet getImports() {
        return imports;
    }

    public ClassFileProvider getFileProvider() {
        return fileProvider;
    }

    public void setSignature(SignatureInfos signature) {
        this.signature = signature;
    }

    public void addField(String name, AbstractFieldFormat format) {
        fieldFormats.put(name, format);
    }

    public void fireVisit(ClassEntryVisitor visitor) {
        if (packageName != null)
            visitor.visitPackage(packageName);
        if (!imports.isEmpty()) {
            ImportEntryVisitor iev = visitor.visitImports();
            if (iev != null)
                imports.fireVisit(iev);
        }
        if (signature != null) {
            SignatureVisitor sv = visitor.visitSignature();
            if (sv != null)
                signature.fireVisit(sv);
        }
    }

    @Override
    public String toString() {
        return StringUtils.fieldsAsString(getClass(),
                new String[]{"className", "packageName", "accessFlag", "superClass", "interfaces"},
                className, packageName, accessFlag + "", superClass, Arrays.toString(interfaces));
    }
}
