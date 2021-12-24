/*
 * Copyright 2021 Nickid2018
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.nickid2018.dejava;

import java.util.List;
import java.util.regex.Pattern;

public class ConstantNames {

    // Keywords for Java
    public static final String PUBLIC = "public";
    public static final String PROTECTED = "protected";
    public static final String PRIVATE = "private";
    public static final String PACKAGE = "package";
    public static final String CLASS = "class";
    public static final String INTERFACE = "interface";
    public static final String ENUM = "enum"; // Added in Java 5
    public static final String ANNOTATION = "@interface";
    public static final String IMPORT = "import";
    public static final String STATIC = "static";
    public static final String FINAL = "final";
    public static final String VOID = "void";
    public static final String INT = "int";
    public static final String LONG = "long";
    public static final String FLOAT = "float";
    public static final String DOUBLE = "double";
    public static final String BYTE = "byte";
    public static final String SHORT = "short";
    public static final String BOOLEAN = "boolean";
    public static final String CHAR = "char";
    public static final String EXTENDS = "extends";
    public static final String IMPLEMENTS = "implements";
    public static final String SUPER = "super";
    public static final String TRANSIENT = "transient";
    public static final String VOLATILE = "volatile";
    public static final String NATIVE = "native";
    public static final String SYNCHRONIZED = "synchronized";
    public static final String ABSTRACT = "abstract";
    public static final String STRICTFP = "strictfp";
    public static final String NULL = "null"; // null literal
    public static final String TRUE = "true"; // true literal
    public static final String FALSE = "false"; // false literal
    public static final String THIS = "this";
    public static final String BREAK = "break";
    public static final String CONTINUE = "continue";
    public static final String RETURN = "return";
    public static final String DO = "do";
    public static final String WHILE = "while";
    public static final String IF = "if";
    public static final String ELSE = "else";
    public static final String FOR = "for";
    public static final String INSTANCEOF = "this";
    public static final String SWITCH = "switch";
    public static final String CASE = "case";
    public static final String DEFAULT = "default";
    public static final String TRY = "try";
    public static final String CATCH = "catch";
    public static final String FINALLY = "finally";
    public static final String THROW = "throw";
    public static final String THROWS = "throws";
    public static final String NEW = "new";
    public static final String ASSERT = "assert"; // Added in J2SE 1.4
    // Reserved keywords for Java
    public static final String GOTO = "goto";
    public static final String CONST = "const";
    public static final String UNDERSCORE = "_"; // Can't be an identifier after Java 9
    // Contextual keywords
    public static final String RECORD = "record"; // RecordDeclaration
    public static final String YIELD = "yield"; // YieldStatement
    public static final String SEALED = "sealed"; // NormalClassDeclaration
    public static final String NON_SEALED = "non-sealed"; // NormalClassDeclaration
    public static final String PERMITS = "permits"; // NormalClassDeclaration
    public static final String VAR = "var"; // LocalVariableType
    public static final String MODULE = "module"; // ModuleDeclaration
    public static final String OPEN = "open"; // ModuleDeclaration
    public static final String OPENS = "opens"; // ModuleDirective
    public static final String WITH = "with"; // ModuleDirective
    public static final String USES = "uses"; // ModuleDirective
    public static final String TO = "to"; // ModuleDirective
    public static final String EXPORTS = "exports"; // ModuleDirective
    public static final String PROVIDES = "provides"; // ModuleDirective
    public static final String REQUIRES = "requires"; // ModuleDirective
    public static final String TRANSITIVE = "transitive"; // RequiresModifier

    // Invalid names for Java
    public static final List<String> KEYWORDS_ALL_RESTRICTED = List.of(
            PUBLIC, PROTECTED, PRIVATE, PACKAGE, CLASS, INTERFACE, ENUM, IMPORT, STATIC, FINAL, VOID, INT, LONG, FLOAT,
            DOUBLE, BYTE, SHORT, BOOLEAN, CHAR, EXTENDS, IMPLEMENTS, SUPER, TRANSIENT, VOLATILE, NATIVE, SYNCHRONIZED,
            ABSTRACT, STRICTFP, NULL, TRUE, FALSE, THIS, BREAK, CONTINUE, RETURN, DO, WHILE, IF, ELSE, FOR, INSTANCEOF,
            SWITCH, CASE, DEFAULT, TRY, CATCH, FINALLY, THROW, THROWS, NEW, ASSERT, GOTO, CONST, UNDERSCORE
    );
    // Patterns
    public static final Pattern DOT_VALID_NAME = Pattern.compile("[^;/\\[\\]]+(\\.[^;/\\[\\]]+)*");
    // Believe it or not, "123456789 \n\t~!@#$%^&*()_+=-{}|\\:\"'<>,?`" IS A VALID NAME IN JVM
    public static final Pattern VALID_NAME = Pattern.compile("[^;./\\[\\]]+");
    public static final Pattern BEST_NAMING = Pattern.compile("[a-zA-Z0-9$][a-zA-Z0-9$_]+");
    // Bootstrap Methods
    public static final String LAMBDA_BOOTSTRAP_CLASS = "java/lang/invoke/LambdaMetafactory"; // In Java 8
    public static final String LAMBDA_BOOTSTRAP_S_NAME = "metafactory";
    public static final String LAMBDA_BOOTSTRAP_S_DESC = "(Ljava/lang/invoke/MethodHandles$Lookup;" +
            "Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;" +
            "Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;";
    public static final String LAMBDA_BOOTSTRAP_A_NAME = "altMetafactory";
    public static final String LAMBDA_BOOTSTRAP_A_DESC = "(Ljava/lang/invoke/MethodHandles$Lookup;" +
            "Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;";
    public static final String STRING_BOOTSTRAP_CLASS = "java/lang/invoke/StringConcatFactory"; // In Java 9
    public static final String STRING_BOOTSTRAP_S_NAME = "makeConcatWithConstants";
    public static final String STRING_BOOTSTRAP_S_DESC = "(Ljava/lang/invoke/MethodHandles$Lookup;" +
            "Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;";
    public static final String STRING_BOOTSTRAP_A_NAME = "makeConcat";
    public static final String STRING_BOOTSTRAP_A_DESC = "(Ljava/lang/invoke/MethodHandles$Lookup;" +
            "Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;";
    public static final String SWITCH_BOOTSTRAP_CLASS = "java/lang/runtime/SwitchBootstraps";
    public static final String SWITCH_BOOTSTRAP_TYPE_NAME = "typeSwitch"; // In Java 17 Preview
    public static final String SWITCH_BOOTSTRAP_TYPE_DESC = "(Ljava/lang/invoke/MethodHandles$Lookup;" +
            "Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;";
    public static final String SWITCH_BOOTSTRAP_ENUM_NAME = "enumSwitch"; // Unused in Java 17
    public static final String SWITCH_BOOTSTRAP_ENUM_DESC = "(Ljava/lang/invoke/MethodHandles$Lookup;" +
            "Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;";
    public static final String OBJECT_BOOTSTRAP_CLASS = "java/lang/runtime/ObjectMethods"; // Combine with record (14)
    public static final String OBJECT_BOOTSTRAP_NAME = "bootstrap";
    public static final String OBJECT_BOOTSTRAP_DESC = "(Ljava/lang/invoke/MethodHandles$Lookup;" +
            "Ljava/lang/String;Ljava/lang/invoke/TypeDescriptor;Ljava/lang/Class;" +
            "Ljava/lang/String;[Ljava/lang/invoke/MethodHandle;)Ljava/lang/invoke/CallSite;";
}
