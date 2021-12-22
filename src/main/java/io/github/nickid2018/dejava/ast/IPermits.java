package io.github.nickid2018.dejava.ast;

import java.util.*;

public interface IPermits {
    List<Typename> getPermits();

    IPermits setPermits(List<Typename> classPermits);
}
