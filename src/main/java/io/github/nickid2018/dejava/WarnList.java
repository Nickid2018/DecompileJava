/*
 * Copyright 2021 Nickid2018
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.nickid2018.dejava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarnList {

    private static final Map<String, List<String>> warns = new HashMap<>();
    private static boolean enabled = false;

    public static void setEnabled(boolean enabled) {
        WarnList.enabled = enabled;
    }

    public static void warn(String className, String warn, Object... args) {
        if (enabled)
            warns.computeIfAbsent(className, str -> new ArrayList<>()).add(warn.formatted(args));
    }

    public static Map<String, List<String>> getWarns() {
        return warns;
    }

    public static List<String> getWarns(String name) {
        return warns.get(name);
    }
}
