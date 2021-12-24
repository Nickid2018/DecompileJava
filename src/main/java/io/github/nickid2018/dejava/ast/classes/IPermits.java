package io.github.nickid2018.dejava.ast.classes;

import java.util.*;

import io.github.nickid2018.dejava.ast.Typename;

public interface IPermits {
    List<Typename> getPermits();

    IPermits setPermits(List<Typename> classPermits);
}
