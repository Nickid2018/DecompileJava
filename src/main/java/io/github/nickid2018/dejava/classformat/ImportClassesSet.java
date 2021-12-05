package io.github.nickid2018.dejava.classformat;

import io.github.nickid2018.dejava.api.ImportEntryVisitor;
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
    public void addImport(String name) {
        // Skip head array type, "LXX;".
        name = ClassNameUtils.getTypeName(name);
        // Check primitive class
        if (ClassNameUtils.isPrimitive(name))
            return;
        boolean innerClass = false;
        if (name.contains("$")) {
            // Inner Class?
            innerClass = true;
        } else
            name = name.replace('/', '.');
        if(name.startsWith("java.lang") && !innerClass)
            return;
        if(name.startsWith(nowPackage) && !innerClass)
            return;
        names.add(name);
    }

    public void fireVisit(ImportEntryVisitor visitor) {
        names.forEach(visitor::visitImportEntry);
    }
}
