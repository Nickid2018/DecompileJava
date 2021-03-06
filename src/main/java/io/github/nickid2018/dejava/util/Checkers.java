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

package io.github.nickid2018.dejava.util;

import io.github.nickid2018.dejava.DecompileException;

import java.util.regex.Pattern;

public class Checkers {

    public static void errorIfTrue(boolean check, String error, Object... args) throws DecompileException {
        if (check)
            throw new DecompileException(error, args);
    }

    public static void errorIfFalse(boolean check, String error, Object... args) throws DecompileException {
        if (!check)
            throw new DecompileException(error, args);
    }

    public static boolean match(String str, Pattern pattern) {
        return str != null && pattern.matcher(str).matches();
    }

    public static void errorIfMatches(String str, Pattern pattern, String error, Object... args) throws DecompileException {
        errorIfTrue(match(str, pattern), error, args);
    }

    public static void errorIfNotMatches(String str, Pattern pattern, String error, Object... args) throws DecompileException {
        errorIfFalse(match(str, pattern), error, args);
    }
}
