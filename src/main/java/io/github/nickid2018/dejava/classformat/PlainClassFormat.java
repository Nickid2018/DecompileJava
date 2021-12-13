package io.github.nickid2018.dejava.classformat;

import io.github.nickid2018.dejava.DecompileException;
import io.github.nickid2018.dejava.api.ClassFileProvider;

public class PlainClassFormat extends AbstractClassFormat {

    /**
     * Create an instance with certain internal name.
     *
     * @param name       Internal Name
     * @param accessFlag Access flag for the class
     * @param superClass Super class for the class
     * @param interfaces The implemented classes
     */
    public PlainClassFormat(String name, int accessFlag, String superClass, String[] interfaces, ClassFileProvider provider)
            throws DecompileException {
        super(name, accessFlag, superClass, interfaces, provider);
    }
}
