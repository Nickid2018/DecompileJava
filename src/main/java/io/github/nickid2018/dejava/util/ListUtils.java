package io.github.nickid2018.dejava.util;

import java.util.*;

public class ListUtils {
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
    public static boolean notEmpty(List<?> list) {
        return !isEmpty(list);
    }
    public static <T> T getOrDefault(List<T> list, int index, T defaultValue) {
        return isEmpty(list) || index >= list.size() ? defaultValue : list.get(index);
    }
}
