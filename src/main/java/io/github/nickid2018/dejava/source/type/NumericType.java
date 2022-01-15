package io.github.nickid2018.dejava.source.type;

import io.github.nickid2018.dejava.ConstantNames;

public interface NumericType extends PrimitiveType {
    Int BYTE = Int.BYTE;
    Int SHORT = Int.SHORT;
    Int INT = Int.INT;
    Int LONG = Int.LONG;
    Int CHAR = Int.CHAR;
    Float FLOAT = Float.FLOAT;
    Float DOUBLE = Float.DOUBLE;

    enum Int {
        BYTE, SHORT, INT, LONG, CHAR;

        public static String toString(Int input) {
            return switch (input) {
                case BYTE -> ConstantNames.BYTE;
                case SHORT -> ConstantNames.SHORT;
                case INT -> ConstantNames.INT;
                case LONG -> ConstantNames.LONG;
                case CHAR -> ConstantNames.CHAR;
            };
        }
    }

    enum Float {
        FLOAT, DOUBLE;

        public static String toString(Float input) {
            return switch (input) {
                case FLOAT -> ConstantNames.FLOAT;
                case DOUBLE -> ConstantNames.DOUBLE;
            };
        }
    }
}
