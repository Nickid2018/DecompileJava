package io.github.nickid2018.dejava.ast;
/**
 * Logical AST Node. The parent of all AST nodes.
 */
public interface INode {
    /**
     * Convert the node to source code string.
     * 
     * @return rendered string
     */
    String toSource(FormatControl fc);

    default String toSource() {
        return toSource(FormatControl.getDefault());
    }
}
