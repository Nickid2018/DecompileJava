package io.github.nickid2018.dejava.ast;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.github.nickid2018.dejava.ConstantNames;

public class ClassNode implements INode{

  public enum ClassModifier {
    PUBLIC    (ConstantNames.PUBLIC, 0), 
    PROTECTED (ConstantNames.PROTECTED, 0),
    PRIVATE   (ConstantNames.PRIVATE, 0), 
    ABSTRACT  (ConstantNames.ABSTRACT, 1), 
    STATIC    (ConstantNames.STATIC, 2), 
    FINAL     (ConstantNames.FINAL, 4), 
    STRICTFP  (ConstantNames.STRICTFP, 3);

    public final String name;
    public final int priority;
    ClassModifier(String keyword, int priority) {
      this.name = keyword;
      this.priority = priority;
    }

    public String toString() {
      return name;
    }

    public static SortedSet<ClassModifier> of(ClassModifier... modifiers) {
      return new TreeSet<>(Set.of(modifiers));
    }
  }

  public enum ClassType {
    CLASS(ConstantNames.CLASS),
    INTERFACE(ConstantNames.INTERFACE), 
    ENUM(ConstantNames.ENUM), 
    RECORD(ConstantNames.RECORD);

    public final String name;
    ClassType(String keyword) {
      this.name = keyword;
    }

    public String toString() {
      return name;
    }
  }

  private SortedSet<ClassModifier> modifiers;
  private ClassType type;
  private Typename typename;
  
  public ClassNode(Typename typename, ClassType type, SortedSet<ClassModifier> modifiers) {
    this.type = type;
    this.typename = typename;
    this.modifiers = modifiers;
  }

  public ClassNode(Typename typename, SortedSet<ClassModifier> modifiers) {
    this(typename, ClassType.CLASS, modifiers);
  }

  public String getModifiersString() {
    return modifiers.stream()
      .map(ClassModifier::toString)
      .collect(Collectors.joining(" "));
  }

  public String toString() {
    return getModifiersString() + " " + type + " " + typename.getIdentifier() + " {}";
  }

  @Override
  public String getType() {
    return "class";
  }
}
