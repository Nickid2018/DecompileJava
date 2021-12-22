package io.github.nickid2018.dejava.ast;

import java.util.*;

public interface IImplements {
    List<Typename> getImplements();

    IImplements setImplements(List<Typename> classImplements);
}
