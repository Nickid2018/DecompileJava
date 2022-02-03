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

/**
 * Pair with int and object.
 * @param <T> object type
 * @param key key of the pair
 * @param value value of the pair
 */
public record IntPair<T>(int key, T value) implements Comparable<IntPair<T>> {

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(IntPair<T> pair) {
        if (key != pair.key)
            return key > pair.key ? 1 : -1;
        if (pair.value instanceof Comparable comparable)
            if (value instanceof Comparable thisValue)
                return thisValue.compareTo(comparable);
        return System.identityHashCode(value) - System.identityHashCode(pair.value);
    }
}
