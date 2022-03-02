package com.anas.jsimpletexteditor.files;

import java.io.File;
import java.util.Locale;

public enum FileType {
    PLAIN_TEXT("text"),
    MARKDOWN("md"),
    HTML("html", "htm"),
    JAVA("java"),
    C("c"),
    CPP("cpp"),
    PYTHON("py"),
    SQL("sql"),
    JAVASCRIPT("js"),
    CSS("css"),
    XML("xml"),
    JSON("json"),
    YAML("yaml", "yml"),
    RUBY("rb"),
    PHP("php"),
    BASH("sh"),
    GO("go"),
    LUA("lua");
    private final String[] extensions;

    private FileType(String ... extensions) {
        this.extensions = extensions;
    }

    public String getExtension() {
        return extensions[0];
    }

    public static FileType getFileType(File file) {
        String name = file.getName();

        if (!name.contains(".") || PLAIN_TEXT.hasFileTheSameExtension(file)) {
            return PLAIN_TEXT;
        }

        for (FileType fileType : FileType.values()) {
            if (fileType.hasFileTheSameExtension(file)) {
                return  fileType;
            }
        }
        return FileType.PLAIN_TEXT;
    }

    public boolean hasFileTheSameExtension(File file) {
        String extension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
        for (String e : extensions) {
            if (e.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase().replace("_", " ") + " file";
    }
}
