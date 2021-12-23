package io.github.nickid2018.dejava.ast.expr;

import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.ast.INode;

public class IExpression implements INode {

    @Override
    public String toSource(FormatControl fc) {
        return "IExpression";
    }
    
}
