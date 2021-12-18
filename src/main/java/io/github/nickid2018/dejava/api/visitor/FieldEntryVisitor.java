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

import org.objectweb.asm.signature.SignatureVisitor;

/**
 * Visitor for field. The order is:
 * <code>(visitAnnotation)(visitSignature)[visitNoInitialValue|visitInitialValue|visitInitialWithStatement]visitEnd</code>
 */
public interface FieldEntryVisitor {

    /**
     * Visit when the field has annotations.
     * @param name the class name of the annotation
     * @return a annotation visitor
     */
    AnnotationEntryVisitor visitAnnotation(String name);

    /**
     * Visit when the field have templates.
     * @return a signature information visitor
     */
    SignatureVisitor visitSignature();

    /**
     * Visit when the field doesn't have an initial value can be parsed in a line.
     */
    void visitNoInitialValue();

    /**
     * Visit when the field have an initial value with a constant.
     * @param initialValue the initial value of the field
     */
    void visitInitialValue(String initialValue);

    /**
     * Visit when the field is assigned with a statement(including lambda).
     * @return a statement visitor
     */
    StatementEntryVisitor visitInitialWithStatement();

    /**
     * Visit when the field has been read.
     */
    void visitEnd();
}
