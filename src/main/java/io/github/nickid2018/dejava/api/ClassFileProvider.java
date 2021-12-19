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

package io.github.nickid2018.dejava.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A provider for classes.
 */
public interface ClassFileProvider {

    /**
     * Warp the byte array into a stream.
     * @param bytes a byte array
     * @return a stream contains the data in the array
     */
    static InputStream warpBytes(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    /**
     * Return whether a class is an inner class.
     * @param name internal name of the class
     * @return true if the class is an inner class
     */
    boolean isInnerClass(String name);

    /**
     * Return whether a class is in the certain package.
     * @param packageName name of the package
     * @param binaryName binary name of the class
     * @return true if the class is in the package
     */
    boolean isInPackage(String packageName, String binaryName);

    /**
     * Resolve an internal name into a binary name.
     * @param internalName internal name of the class
     * @return binary name of the class
     */
    String resolveBinaryName(String internalName);

    /**
     * Get the stream contains the data of the class.
     * @param internalName internal name of the class
     * @return a stream contains the class data
     * @throws IOException occurs in opening/reading the class file
     */
    InputStream getClassFile(String internalName) throws IOException;
}
