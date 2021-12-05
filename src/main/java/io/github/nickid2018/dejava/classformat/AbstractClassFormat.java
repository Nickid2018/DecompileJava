package io.github.nickid2018.dejava.classformat;

import io.github.nickid2018.dejava.Checkers;
import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.WarnList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    protected final String className;
    protected final String packageName;
    protected final int accessFlag;

    protected final ImportClassesSet imports;

    /**
     * Create an instance with certain internal name.
     *
     * @param name Internal Name
     */
    public AbstractClassFormat(String name, int accessFlag) throws DecompileException {
        this.accessFlag = accessFlag;
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
}
