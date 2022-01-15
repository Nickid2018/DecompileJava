package io.github.nickid2018.dejava.source;

import io.github.nickid2018.dejava.ast.FormatControl;
import io.github.nickid2018.dejava.ast.SourceBuilder;
import io.github.nickid2018.dejava.source.type.*;

import java.util.List;
import java.util.Objects;

public class TypeFormats {
    public static String format(FormatControl fc, Dims input) {
        if (input == null) return "";
        return "[]".repeat(Objects.requireNonNullElse(input.annotations(), List.of()).size());
    }

    public static String format(FormatControl fc, Type input) {
        if (input == null) return "";
        if (input instanceof ClassType classType) {
            // Example: io.github.nickid2018.dejava.Object.Internal
            return new SourceBuilder(fc)
                    // prefix
                    .doIfTrue(classType.prefix() != null, (sb) -> sb
                            .appendText(classType.prefix().toSource(fc))
                            .appendText("."))
                    // identifier
                    .appendText(classType.identifier())
                    .build();
        }
        if (input instanceof ArrayType arrayType) {
            // Example: Object[][]
            return new SourceBuilder(fc)
                    // type
                    .appendText(arrayType.type().toSource())
                    // dims
                    .appendText(arrayType.dims().toSource(fc))
                    .build();
        }
        if (input instanceof PrimitiveType || input instanceof TypeVariable) {
            return ((IdentifierProvider) input).identifier();
        }

        throw new UnsupportedOperationException();
    }
}
