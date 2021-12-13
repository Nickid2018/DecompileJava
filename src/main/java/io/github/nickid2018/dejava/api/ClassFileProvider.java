package io.github.nickid2018.dejava.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface ClassFileProvider {

    boolean isInnerClass(String name);

    boolean isInPackage(String packageName, String binaryName);

    String resolveBinaryName(String internalName);

    InputStream getClassFile(String internalName) throws IOException;

    static InputStream warpBytes(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }
}
