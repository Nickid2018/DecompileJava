package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.*;
import io.github.nickid2018.dejava.api.*;

import java.util.*;

public class ModuleDirective implements INode, IModifiable {
    private String packageName;
    private Typename typename;
    private List<Typename> typenameList = new ArrayList<>();
    private List<String> moduleNameList = new ArrayList<>();
    protected ModifierList modifiers = new ModifierList();
    private Type type;

    @Override
    public List<IModifier> getModifiers() {
        return this.modifiers.getModifiers();
    }

    @Override
    public ModuleDirective addModifiers(IModifier... modifiers) {
        this.modifiers.addModifiers(modifiers);
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        var wr = new StructuralWriter(fc);

        wr.token(type.toString());
        switch (getType()) {
            // {RequiresModifier} ModuleName;
            case Requires -> wr.token(getModifiersString())
                    .token(getModuleNameList().isEmpty() ? "" : getModuleNameList().get(0));

            // PackageName [to ModuleName {, ModuleName}];
            case Exports, Opens -> {
                wr.token(getPackageName())
                        .doIfTrue(!getModuleNameList().isEmpty(), (writer -> writer.token(ConstantNames.TO)))
                        .token(getModuleNameList().get(0));

                for (int i = 1; i < getModuleNameList().size(); i++) {
                    wr.token(",").token(getModuleNameList().get(i));
                }
            }

            // TypeName
            case Uses -> wr.token(getTypename().toSource(fc));

            // TypeName with TypeName {, TypeName};
            case Provides -> {
                wr.token(getTypename().toSource(fc))
                        .doIfTrue(!getTypenameList().isEmpty(), (writer -> writer.token(ConstantNames.WITH)))
                        .token(getTypenameList().get(0).toSource(fc));

                for (int i = 1; i < getTypenameList().size(); i++) {
                    wr.token(",").token(getTypenameList().get(i).toSource(fc));
                }
            }
            default -> throw new IllegalStateException();
        }
        wr.token(";").line();

        return wr.toSource();
    }

    public enum Type {
        Requires,
        Exports,
        Opens,
        Uses,
        Provides;

        public String toString() {
            return switch (this) {
                case Uses -> ConstantNames.USES;
                case Opens -> ConstantNames.OPENS;
                case Exports -> ConstantNames.EXPORTS;
                case Provides -> ConstantNames.PROVIDES;
                case Requires -> ConstantNames.REQUIRES;
            };
        }
    }

    public ModuleDirective setType(Type type) {
        this.type = type;
        return this;
    }

    public ModuleDirective setModuleNameList(List<String> moduleNameList) {
        this.moduleNameList = moduleNameList;
        return this;
    }

    public ModuleDirective setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public ModuleDirective setTypename(Typename typename) {
        this.typename = typename;
        return this;
    }

    public ModuleDirective setTypenameList(List<Typename> typenameList) {
        this.typenameList = typenameList;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Typename getTypename() {
        return typename;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<String> getModuleNameList() {
        return moduleNameList;
    }

    public List<Typename> getTypenameList() {
        return typenameList;
    }
}
