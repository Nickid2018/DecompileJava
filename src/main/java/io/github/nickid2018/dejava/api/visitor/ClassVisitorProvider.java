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

package io.github.nickid2018.dejava.api.visitor;

import io.github.nickid2018.dejava.api.ClassType;

/**
 * A provider for class visitors
 */
public interface ClassVisitorProvider {

    /**
     * Visit a new class.
     * @param type type of the class
     * @param file file of the class
     * @param isInnerClass true if the class is an inner class
     * @return a visitor to visit class
     */
    ClassEntryVisitor visitClass(ClassType type, String file, boolean isInnerClass);
}
