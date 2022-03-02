package com.anas.jsimpletexteditor.files;


import java.io.File;

public class TextFile extends File {
    private FileType fileType;
    public TextFile(String pathname) {
        super(pathname);
        fileType = initType();
    }

    private FileType initType() {
        String name = getName();
        if (!name.contains(".") || name.endsWith(".txt")) {
            return FileType.PLAIN_TEXT;
        }

        String extension = name.substring(name.lastIndexOf(".") + 1);
        return switch (extension) {
            case "java" -> FileType.JAVA;
            case "c" ->  FileType.C;
            case "cpp" -> FileType.CPP;
            case "py" -> FileType.PYTHON;
            case "js" -> FileType.JAVASCRIPT;
            case "html" -> FileType.HTML;
            case "css" -> FileType.CSS;
            case "xml" -> FileType.XML;
            case "json" -> FileType.JSON;
            case "sql" -> FileType.SQL;
            case "php" -> FileType.PHP;
            case "rb" -> FileType.RUBY;
            case "md" -> FileType.MARKDOWN;
            default -> FileType.PLAIN_TEXT;
        };
    }

    public FileType getType() {
        return fileType;
    }

    public void setType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getText() {
        return "";
    }
}
