package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.util.*;

import java.util.*;
import java.util.function.*;

public class SourceBuilder {
    private List<String> finishedLines = new ArrayList<>();
    private StringBuilder currentLineBuilder = new StringBuilder();
    private FormatControl formatControl;
    private int currentIndentLevel;

    public SourceBuilder() {
        this(FormatControl.getDefault());
    }

    public SourceBuilder(FormatControl formatControl) {
        this.formatControl = formatControl;
    }

    public SourceBuilder appendAllNode(
            String separator,
            List<? extends INode> nodes) {
        if (nodes == null) return this;
        boolean shouldInsertSeparator = true;
        for (var node : nodes) {
            if (shouldInsertSeparator) {
                appendNode(node).appendText(separator).appendIntervalSpace();
            } else {
                appendNode(node).appendIntervalSpace();
            }
            shouldInsertSeparator = false;
        }
        return this;
    }

    private SourceBuilder appendIndent() {
        // TODO support isUseSpace
        return appendString("\t");
    }

    public SourceBuilder doIfTrue(boolean condition, Consumer<SourceBuilder> b) {
        if (condition) b.accept(this);
        return this;
    }

    public SourceBuilder appendIsolationText(String text) {
        return appendIntervalSpace().appendText(text);
    }

    public SourceBuilder appendIntervalSpace() {
        if (currentLineBuilder.length() == 0
                || ' ' == getEndChar()) {
            return this;
        }
        appendText(" ");
        return this;
    }

    public SourceBuilder appendNode(INode node) {
        if (node == null) return this;
        appendIsolationText(node.toSource(formatControl));
        return this;
    }

    private SourceBuilder appendString(String string) {
        currentLineBuilder.append(string);
        return this;
    }

    public SourceBuilder appendText(String str) {
        if (StringUtils.notEmpty(str)) {
            boolean isFirstLine = true;
            for (var line : str.lines().toList()) {
                if (isFirstLine) {
                    tryIndent();
                } else {
                    breakLine();
                }
                appendString(line);
                isFirstLine = false;
            }
        }
        return this;
    }

    private SourceBuilder beginIndent() {
        currentIndentLevel++;
        return this;
    }

    public SourceBuilder breakLine() {
        finishCurrentLine();
        tryIndent();
        return this;
    }

    public String build() {
        finishCurrentLine();
        var sb = new StringBuilder();
        boolean br = false;
        for (var line : finishedLines) {
            if (br) {
                sb.append("\n");
            }
            sb.append(line);
            br = true;
        }
        return sb.toString();
    }

    public SourceBuilder child(Consumer<SourceBuilder> consumer) {
        beginIndent();
        var child = new SourceBuilder(this.formatControl);
        consumer.accept(child);
        appendText(child.build());
        endIndent();
        return this;
    }

    private SourceBuilder endIndent() {
        Math.max(0, --currentIndentLevel);
        return this;
    }

    private SourceBuilder finishCurrentLine() {
        if (currentLineBuilder.length() > 0) {
            finishedLines.add(currentLineBuilder.toString());
            currentLineBuilder.setLength(0);
        }
        return this;
    }

    private char getEndChar() {
        return currentLineBuilder.charAt(currentLineBuilder.length() - 1);
    }

    public FormatControl getFormatControl() {
        return formatControl;
    }

    private SourceBuilder tryIndent() {
        // should indent
        if (currentLineBuilder.length() == 0) {
            for (int i = 0; i < currentIndentLevel; i++) {
                appendIndent();
            }
        }
        return this;
    }
}
