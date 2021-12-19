package io.github.nickid2018.dejava.ast;

public class AnnotationDecl implements IModifier {

  private String name;

  public AnnotationDecl(String name) {
    this.name = name;
  }

  @Override
  public int compareTo(IModifier o) {
    if(!o.isAnnotation()) return -999;
    if (o instanceof AnnotationDecl a) {
      return this.getName().compareTo(o.getName());
    }
    return 0;
  }

  @Override
  public boolean isAnnotation() {
    return true;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toSource(FormatControl fc) {
    return "@" + this.name;
  }
  
}
