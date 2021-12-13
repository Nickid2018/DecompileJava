package io.github.nickid2018.dejava.classformat;

import io.github.nickid2018.dejava.api.ClassFileProvider;
import io.github.nickid2018.dejava.api.visitor.ClassEntryVisitor;
import io.github.nickid2018.dejava.fieldformat.AbstractFieldFormat;
import io.github.nickid2018.dejava.methodformat.AbstractMethodFormat;
import io.github.nickid2018.dejava.util.Checkers;
import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.WarnList;

import java.util.*;

import static io.github.nickid2018.dejava.ConstantNames.*;

public abstract class AbstractClassFormat {

    public static final List<String> INVALID_CLASS_NAMES;

    static {
        List<String> keywords = new ArrayList<>(KEYWORDS_ALL_RESTRICTED);
        keywords.addAll(List.of(
                RECORD, SEALED, PERMITS
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
     */
    public AbstractClassFormat(String name, int accessFlag, String superClass, String[] interfaces, ClassFileProvider provider)
            throws DecompileException {
        this.accessFlag = accessFlag;
        fileProvider = provider;
        int split = Math.max(-1, name.lastIndexOf('/'));
        className = name.substring(split + 1);
        packageName = split > 0 ? name.substring(0, split).replace('/', '.') : null;
        // --- Verify class name
        Checkers.checkIfTrue(className.isEmpty(), "Invalid class name: empty");
        Checkers.checkIfTrue(INVALID_CLASS_NAMES.contains(className),
                "Invalid class name: %s is a keyword or reserved word", className);
        Checkers.checkIfFalse(VALID_NAME.matcher(className).matches(),
                "Invalid class name: %s has illegal characters", className);
        if (!BEST_NAMING.matcher(className).matches())
            WarnList.warn("%s isn't a good class name: have non-ASCII character");
        imports = new ImportClassesSet(packageName);
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

    public void fireVisit(ClassEntryVisitor visitor) {
        if (packageName != null)
            visitor.visitPackage(packageName);
        if (!imports.isEmpty())
            imports.fireVisit(visitor.visitImports());
        if (signature != null)
            signature.fireVisit(visitor.visitSignature());
    }
}
