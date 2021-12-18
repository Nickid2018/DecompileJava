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

import io.github.nickid2018.dejava.DecompileSettings;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class MarkWriter {

    private final Map<String, Integer> marks = new HashMap<>();
    private final List<Integer> markPositions = new ArrayList<>();
    private final List<LinePair> lines = new ArrayList<>();

    private int maxIndent = 0;
    private int indentNowLine = 0;

    private String markNow;
    private int markPosition = 0;
    private int jumpLineAdds = 0;

    public MarkWriter mark(String name) {
        marks.put(name, markPositions.size());
        markPositions.add(lines.size());
        return this;
    }

    public MarkWriter indent() {
        maxIndent = Math.max(maxIndent, ++indentNowLine);
        return this;
    }

    public MarkWriter unindent() {
        indentNowLine--;
        return this;
    }

    public MarkWriter newLine() {
        return newLine("");
    }

    public MarkWriter newLine(String str, Object... args) {
        return newLine(indentNowLine, str, args);
    }

    public MarkWriter newLine(int indent, String str, Object... args) {
        LinePair element = new LinePair(indent, str.formatted(args));
        if (markNow == null)
            lines.add(element);
        else
            lines.add(markPosition + (jumpLineAdds++), element);
        return this;
    }

    public MarkWriter jump(String mark) {
        markNow = mark;
        if (marks.containsKey(mark))
            throw new IllegalArgumentException("Unknown Mark");
        markPosition = markPositions.get(marks.get(mark));
        return this;
    }

    public MarkWriter resume() {
        markNow = null;
        for (int i = 0; i < markPositions.size(); i++) {
            int pos = markPositions.get(i);
            if (pos >= markPosition)
                markPositions.set(i, pos + jumpLineAdds);
        }
        markPosition = 0;
        jumpLineAdds = 0;
        return this;
    }

    public String outputAsString() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

        // Pre-generate indent spaces
        String[] indentPreGenerate = new String[maxIndent];
        char[] cellArray = new char[DecompileSettings.indentSpace];
        Arrays.fill(cellArray, ' ');
        String cell = new String(cellArray);
        if (maxIndent > 0) {
            indentPreGenerate[0] = cell;
            for (int i = 1; i < maxIndent; i++)
                indentPreGenerate[i] = indentPreGenerate[i - 1] + cell;
        }

        // Generate lines
        for (LinePair pair : lines)
            builder.append(indentPreGenerate[pair.indent()]).append(pair.lineStr()).append(lineSeparator);
        return builder.toString();
    }

    public void outputToWriter(Writer writer) throws IOException {
        writer.write(outputAsString());
    }
}
