package io.github.nickid2018.dejava.util;

public class ClassNameUtils {

    public static boolean isPrimitive(String typeName) {
        return typeName.equals("B") || typeName.equals("C") || typeName.equals("D") || typeName.equals("F") ||
               typeName.equals("I") || typeName.equals("J") || typeName.equals("S") || typeName.equals("Z") ||
               typeName.equals("V");
    }

    public static String getTypeName(String qualifiedName) {
        qualifiedName = qualifiedName.substring(Math.max(0, 1 + qualifiedName.lastIndexOf('[')));
        return qualifiedName.startsWith("L") && qualifiedName.endsWith(";") ?
                qualifiedName.substring(1, qualifiedName.length() - 1) : qualifiedName;
    }
}
