package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StructuralWriter {
    private final ArrayList<StringBuilder> arr = new ArrayList<>();
    private final int indentSpaces = 4; // TODO move to FormatControl
    private final FormatControl format;
    private int indent = 0;
    private int line = 0;

    public StructuralWriter(FormatControl fc) {
        this(fc, 0);
    }

    public StructuralWriter(FormatControl fc, int indent) {
        this.indent = indent;
        this.format = fc;
        arr.add(new StringBuilder(" ".repeat(indent * indentSpaces)));
    }

    public static StructuralWriter newInstance() {
        return new StructuralWriter(FormatControl.getDefault());
    }

    public StructuralWriter token(String... s) {
        return tokenSep(" ", s);
    }

    public StructuralWriter token(List<? extends INode> nodes) {
        if(ListUtils.isEmpty(nodes)) return this;
        return token(nodes.stream().map(ss -> ss.toSource(format)).toArray(String[]::new));
    }

    public StructuralWriter tokenSep(String sep, String... s) {
        StringBuilder sb = arr.get(line);
        if (!sb.isEmpty() && sb.charAt(sb.length() - 1) != ' ') {
            sb.append(" ");
        }
        sb.append(String.join(sep, s));
        return this;
    }

    public StructuralWriter tokenSep(String sep, List<? extends INode> s) {
        return tokenSep(sep, s.stream().map(e -> e.toSource(format)).toArray(String[]::new));
    }

    public StructuralWriter append(String s) {
        if (StringUtils.isEmpty(s)) return this;
        List<String> t = s.lines().toList();
        arr.get(line).append(t.get(0));
        t.stream().skip(1).forEach(e -> line(e));
        return this;
    }

    public StructuralWriter line() {
        return line("");
    }

    public StructuralWriter line(String s) {
        arr.add(new StringBuilder(" ".repeat(indent * indentSpaces)).append(s));
        line++;
        return this;
    }

    public StructuralWriter statement(String... s) {
        return line().token(s).append(";");
    }

    public StructuralWriter token(INode node) {
        if(node != null) token(node.toSource(format));
        return this;
    }

    public StructuralWriter enter() {
        indent++;
        line();

        return this;
    }

    public StructuralWriter exit() {
        indent--;
        line();

        return this;
    }

    public StructuralWriter place(StructuralWriter t) {
        t.arr.forEach(e -> arr.add(e));
        return this;
    }

    public StructuralWriter block(Consumer<StructuralWriter> blk) {
        token("{");
        StructuralWriter sw = new StructuralWriter(format, indent + 1);
        blk.accept(sw);
        place(sw);
        line("}");

        return this;
    }

    public <T> StructuralWriter doIf(List<T> t, BiConsumer<List<T>, StructuralWriter> b) {
        if (ListUtils.notEmpty(t)) b.accept(t, this);
        return this;
    }

    public StructuralWriter doIf(String s, BiConsumer<String, StructuralWriter> b) {
        if (StringUtils.notEmpty(s)) b.accept(s, this);
        return this;
    }

    public StructuralWriter doIfTrue(boolean condition, Consumer<StructuralWriter> b) {
        if (condition) b.accept(this);
        return this;
    }

    public StructuralWriter doIf(INode s, BiConsumer<INode, StructuralWriter> b) {
        if (s != null) b.accept(s, this);
        return this;
    }

    public String toSource() {
        return arr.stream().collect(Collectors.joining("\n"));
    }
}
