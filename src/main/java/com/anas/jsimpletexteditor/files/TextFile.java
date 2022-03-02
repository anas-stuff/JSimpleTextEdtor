package com.anas.jsimpletexteditor.files;


import java.io.File;
import java.util.StringJoiner;

public class TextFile extends File {
    private Type type;
    public TextFile(String pathname) {
        super(pathname);
        type = initType();
    }

    private Type initType() {
        String name = getName();
        if (!name.contains(".") || name.endsWith(".txt")) {
            return Type.PLAIN_TEXT;
        }

        String extension = name.substring(name.lastIndexOf(".") + 1);
        return switch (extension) {
            case "java" -> Type.JAVA;
            case "c" ->  Type.C;
            case "cpp" -> Type.CPP;
            case "py" -> Type.PYTHON;
            case "js" -> Type.JAVASCRIPT;
            case "html" -> Type.HTML;
            case "css" -> Type.CSS;
            case "xml" -> Type.XML;
            case "json" -> Type.JSON;
            case "sql" -> Type.SQL;
            case "php" -> Type.PHP;
            case "rb" -> Type.RUBY;
            case "sh" -> Type.SHELL;
            case "md" -> Type.MARKDOWN;
            default -> Type.PLAIN_TEXT;
        };
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return "";
    }
}
