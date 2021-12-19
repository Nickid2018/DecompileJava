package io.github.nickid2018.dejava.ast;

public interface IModifier extends INode, Comparable<IModifier> {
    boolean isAnnotation();

    String getName();
}
