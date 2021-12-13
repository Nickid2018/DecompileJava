package io.github.nickid2018.dejava.api.visitor;

/**
 * Visitor for import statements. The order is:
 * <code>(visitImportEntry)</code>
 */
public interface ImportEntryVisitor {

    void visitImportEntry(String className);
}
