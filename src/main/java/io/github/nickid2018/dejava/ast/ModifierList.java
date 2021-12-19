package io.github.nickid2018.dejava.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModifierList {
  private ArrayList<IModifier> modifiers = new ArrayList<>();

  public ModifierList addModifiers(IModifier ...modifier) {
    modifiers.addAll(Arrays.asList(modifier));
    return this;
  }

  public List<IModifier> getModifiers() {
    return modifiers;
  }
  
  public String toString() {
    return modifiers
      .stream()
      .sorted()
      .reduce(
        "", 
        (cur, acc) -> cur + acc.toSource() + (acc.isAnnotation() ? "\n" : " "), 
        (a, b) -> a + b)
      .stripTrailing();
  }

}
