package io.github.nickid2018.dejava;

import java.util.ArrayList;
import java.util.List;

public class WarnList {

    private static boolean enabled = false;
    private static final List<String> warns = new ArrayList<>();

    public static void setEnabled(boolean enabled) {
        WarnList.enabled = enabled;
    }

    public static void warn(String warn, Object... args) {
        if(enabled)
            warns.add(warn.formatted(args));
    }

    public static List<String> getWarns() {
        return warns;
    }
}
