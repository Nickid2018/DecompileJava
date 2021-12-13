package io.github.nickid2018.dejava.classformat;

import io.github.nickid2018.dejava.api.ClassFileProvider;
import io.github.nickid2018.dejava.api.visitor.ImportEntryVisitor;
import io.github.nickid2018.dejava.util.ClassNameUtils;

import java.util.HashSet;
import java.util.Set;

public class ImportClassesSet {

    private final String nowPackage;
    private final Set<String> names = new HashSet<>();

    public ImportClassesSet(String nowPackage) {
        this.nowPackage = nowPackage;
    }

    /**
     * Add a class into imports
     * @param name the internal name of the class
     */
    public void addImport(String name, ClassFileProvider provider) {
        // Skip head array type, "LXX;".
        name = ClassNameUtils.getTypeName(name);
        // Check primitive class
        if (ClassNameUtils.isPrimitive(name))
            return;
        boolean innerClass = false;
        if (name.contains("$"))
            // Inner Class?
            innerClass = provider.isInnerClass(name);
        name = provider.resolveBinaryName(name);
        if(name.startsWith("java.lang") && !innerClass)
            return;
        if(provider.isInPackage(nowPackage, name) && !innerClass)
            return;
        names.add(name);
    }

    public boolean isEmpty() {
        return names.isEmpty();
    }

    public void fireVisit(ImportEntryVisitor visitor) {
        names.forEach(visitor::visitImportEntry);
    }
}
