package com.anas.jsimpletexteditor.files;


import java.io.*;

public class TextFile extends File {
    private FileType fileType;
    public TextFile(String pathname) {
        super(pathname);
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
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n")); // Delete last new line
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
}
