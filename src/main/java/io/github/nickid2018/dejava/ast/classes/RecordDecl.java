package io.github.nickid2018.dejava.ast.classes;

import io.github.nickid2018.dejava.ast.*;

public final class RecordDecl extends AbstractClassDecl<RecordDecl> {
    private RecordHeader recordHeader; // TODO add missing new
    public RecordDecl(String identifier) {
        super(identifier);
    }

    public RecordHeader getRecordHeader() {
        return recordHeader;
    }

    public RecordDecl setRecordHeader(RecordHeader recordHeader) {
        this.recordHeader = recordHeader;
        return this;
    }

    // TODO ClassBodyDecl(Nullable)
    // TODO CompactConstructorDeclaration(Nullable)

    @Override
    public String toSource(FormatControl fc) {
        // TODO
        return null;
    }
}
