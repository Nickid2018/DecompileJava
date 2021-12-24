package io.github.nickid2018.dejava.ast.classes;

import io.github.nickid2018.dejava.*;
import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.ast.INode;
import io.github.nickid2018.dejava.ast.ModifierList;
import io.github.nickid2018.dejava.ast.StructuralWriter;
import io.github.nickid2018.dejava.ast.TypeArgumentDecl;

import java.util.*;

/**
 * TODO Rename to NormalClassDecl
 * */
public final class ClassDecl extends AbstractClassDecl<ClassDecl> implements IExtends {
    // TODO Replace to ClassBody
    private final List<INode> children = new ArrayList<>();
    protected ModifierList modifiers = new ModifierList();
    private String classExtends;

    public ClassDecl(String identifier) {
        super(identifier);
    }

    public ClassType getClassType() {
        return ClassType.CLASS;
    }

    public String getExtends() {
        return classExtends;
    }

    public ClassDecl setExtends(String classExtends) {
        this.classExtends = classExtends;
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .append(getModifiersString())
                .token(getClassType().keyword, getIdentifier())
                .doIf(typeArguments, (s, w) -> w.append(TypeArgumentDecl.listToSource(fc, s)))
                .doIf(classExtends, (s, w) -> w.token(ConstantNames.EXTENDS, s))
                .doIf(classImplements, (s, w) -> w.token(ConstantNames.IMPLEMENTS).tokenSep(", ", s.stream().map(e -> e.toSource(fc)).toArray(String[]::new)))
                .doIf(classPermits, (s, w) -> w.token(ConstantNames.PERMITS).tokenSep(", ", s.stream().map(e -> e.toSource(fc)).toArray(String[]::new)))
                .block(b -> {
                    children.forEach(e -> b.line(e.toSource()));
                })
                .toSource();
    }

}
