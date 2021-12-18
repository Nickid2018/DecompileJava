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
