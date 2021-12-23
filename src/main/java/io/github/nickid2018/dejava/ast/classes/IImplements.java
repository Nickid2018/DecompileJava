package io.github.nickid2018.dejava.ast.classes;

import java.util.*;

import io.github.nickid2018.dejava.ast.Typename;

public interface IImplements {
    List<Typename> getImplements();

    IImplements setImplements(List<Typename> classImplements);
}
