package io.github.nickid2018.dejava.ast;

import io.github.nickid2018.dejava.*;

import java.util.*;

public class ModuleDecl implements INode {
    protected String identifier;
    private boolean open;
    private List<ModuleDirective> moduleDirectives = new ArrayList<>();

    public String getIdentifier() {
        return identifier;
    }

    public ModuleDecl setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public List<ModuleDirective> getModuleDirectives() {
        return moduleDirectives;
    }

    public ModuleDecl setModuleDirectives(List<ModuleDirective> moduleDirectives) {
        this.moduleDirectives = moduleDirectives;
        return this;
    }

    public boolean isOpen() {
        return open;
    }

    public ModuleDecl setOpen(boolean open) {
        this.open = open;
        return this;
    }

    @Override
    public String toSource(FormatControl fc) {
        return new StructuralWriter(fc)
                .doIfTrue(open, writer -> writer.token(ConstantNames.OPEN))
                .token(identifier)
                .block(writer -> {
                    writer.token(getModuleDirectives()); // TODO replace to getModuleDirectivesString
                })
                .toSource();
    }
}
