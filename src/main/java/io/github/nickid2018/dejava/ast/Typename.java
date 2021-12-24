package io.github.nickid2018.dejava.ast;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.github.nickid2018.dejava.ConstantNames;
import io.github.nickid2018.dejava.util.DescriptorUtils.DescriptorType;

public class Typename implements INode {
    private String identifier;
    private List<TypeArgumentDecl> typeArguments;
    private int arrayDim = 0;

    public Typename(String identifier, TypeArgumentDecl... typeArguments) {
        this.identifier = identifier;
        this.typeArguments = List.of(typeArguments);
    }

    public Typename(String identifier) {
        this(identifier, new TypeArgumentDecl[0]);
    }

    /**
     * set the array dimension of this type
     * 
     * the type is an array type if dimension is greater than 0
     * 
     * the dimension should be in range [0, 255] <a href="https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html#jvms-4.3.2">to the JVM specifications</a>
     * 
     * @param dimension dimension of the array.
     */
    public Typename setArrayDim(int dim) {
        if (dim < 0) {
            throw new IllegalArgumentException("Array dimension cannot be negative");
        }

        if (dim > 255) {
            throw new IllegalArgumentException("Array dimension cannot be greater than 255");
        }

        this.arrayDim = dim;
        return this;
    }

    public boolean isArray() {
        return arrayDim > 0;
    }

    public int getArrayDim() {
        return arrayDim;
    }

    public static Typename of(String s) {
        return new Typename(s);
    }

    public static List<Typename> ofList(String... s) {
        return Arrays.stream(s).map(Typename::of).collect(Collectors.toList());
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<TypeArgumentDecl> getTypeArguments() {
        return typeArguments;
    }

    /** 
     * @return {@code true} if base type is primitive
     */
    public boolean isBasePrimitive() {
        return switch(identifier) {
            case ConstantNames.BOOLEAN,
                ConstantNames.BYTE,
                ConstantNames.SHORT,
                ConstantNames.INT,
                ConstantNames.LONG,
                ConstantNames.CHAR,
                ConstantNames.FLOAT,
                ConstantNames.DOUBLE -> true;
            default -> false;
        };
    }

    /**
     * @return {@code true} if type is primitive type
     */
    public boolean isPrimitive() {
        return !isArray() && isBasePrimitive();
    }

    /**
     * @param jvmTypename the jvm full qualified name of the type
     * @return the jvm descriptor of the type
     */
    public String toDescriptor(String jvmTypename) {
        StringBuilder sb = new StringBuilder();
        sb.append("[".repeat(arrayDim));
        if (isBasePrimitive()) {
            sb.append(DescriptorType.keyOf(identifier));
        } else {
            // TODO: make Typename full qualified name aware
            sb.append('L').append(jvmTypename).append(';');
        }
        return sb.toString();
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .token(identifier)
                .doIfTrue(!isPrimitive(), (ww) -> 
                    ww.doIf(typeArguments, (e, w) -> w.append(TypeArgumentDecl.listToSource(fc, e))))
                .doIfTrue(isArray(), (w) -> w.append("[]".repeat(arrayDim)))
                .toSource();
    }

}
