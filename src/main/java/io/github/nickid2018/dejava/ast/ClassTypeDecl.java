package io.github.nickid2018.dejava.ast;

public class ClassTypeDecl implements INode {
  private String identifier;
  private ModifierList annotations;
  private TypeArgumentDecl typeArguments;

  @Override
  public String toSource(FormatControl fc) {
    return new StructuralWriter(fc)
      .doIf(annotations.getModifiers(), (e, w) -> w.token(e))
      .token(identifier)
      .doIf(typeArguments, (e, w) -> {
        w.append(e.toSource());
      })
      .toSource();
  }
}
