package io.github.nickid2018.dejava.ast;

import java.util.Arrays;

public class Typename {
  private String identifier;
  private String packageName;

  public Typename(String identifier, String packageName) {
    this.identifier = identifier;
    this.packageName = packageName;
  }

  public static Typename of(String type) {
    String[] id = type.split("\\.");
    if (id.length == 1) {
      return new Typename(id[0], "");
    } else {
      return new Typename(id[id.length - 1], String.join(".", Arrays.copyOfRange(id, 0, id.length - 1)));
    }
  }
  
  public String getIdentifier() {
    return identifier;
  }

  public String getPackageName() {
    return packageName;
  }

  public String toString() {
    return packageName + "." + identifier;
  }

}
