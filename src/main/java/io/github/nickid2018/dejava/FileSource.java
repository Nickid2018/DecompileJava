package io.github.nickid2018.dejava;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface FileSource {

    String[] classes() throws IOException;
    InputStream getInputStream(String className) throws IOException;

    static InputStream warp(byte[] data) {
        return new ByteArrayInputStream(data);
    }
}
