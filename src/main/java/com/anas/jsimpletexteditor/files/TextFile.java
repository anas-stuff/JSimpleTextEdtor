package com.anas.jsimpletexteditor.files;


import java.io.*;

public class TextFile extends File {
    private FileType fileType;
    private String path;
    public TextFile(String pathname) {
        super(pathname);
        this.setPath(super.getPath());
        fileType = FileType.getFileType(new File(pathname));
    }

    public FileType getType() {
        return fileType;
    }

    public void setType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getText() {
        if (super.exists()) {
            if (super.canRead()) {
                return readFile();
            }
        }
        return "";
    }

    private String readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this));

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n")); // Delete last new line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  stringBuilder.toString();
    }

    public boolean save(String path, String newContent) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(newContent);
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return this.path;
    }
}
