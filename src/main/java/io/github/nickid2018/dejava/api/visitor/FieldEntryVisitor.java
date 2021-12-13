package io.github.nickid2018.dejava.api.visitor;

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
    SignatureEntryVisitor visitSignature();

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
