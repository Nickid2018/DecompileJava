package io.github.nickid2018.dejava.source;

import io.github.nickid2018.dejava.source.type.*;
import io.github.nickid2018.dejava.util.Debug;
import io.github.nickid2018.dejava.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TypeTest {
    @Test
    void arrayType() {
        Debug.log(new ArrayType<>(PrimitiveType.newInt(NumericType.Int.BYTE, List.of()),
                new Dims(Lists.newArrayList(new Annotation[]{null}))).toSource());
    }

    @Test
    void booleanType() {
        Debug.log(new BooleanType(List.of()).toSource());
    }

    @Test
    void classType() {
        Debug.log(new ClassType(List.of(), false, "Object", null).toSource());

        Debug.log(new ClassType(List.of(), false, "A",
                new ClassType(List.of(), false, "B", null)).toSource());
    }

    @Test
    void dims() {
        Debug.log(new Dims(List.of(new Annotation[]{null})).toSource());
    }

    @Test
    void numericType() {
        Debug.log(PrimitiveType.newInt(NumericType.BYTE, List.of()).toSource());
        Debug.log(PrimitiveType.newInt(NumericType.SHORT, List.of()).toSource());
        Debug.log(PrimitiveType.newInt(NumericType.INT, List.of()).toSource());
        Debug.log(PrimitiveType.newInt(NumericType.LONG, List.of()).toSource());
        Debug.log(PrimitiveType.newInt(NumericType.CHAR, List.of()).toSource());
        Debug.log(PrimitiveType.newFloat(NumericType.FLOAT, List.of()).toSource());
        Debug.log(PrimitiveType.newFloat(NumericType.DOUBLE, List.of()).toSource());
    }

    @Test
    void typeVar() {
        Debug.log(new TypeVariable(List.of(), "VarType").toSource());
    }

}
