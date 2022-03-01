package com.anas.jsimpletexteditor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serial;
import java.io.Serializable;

public class TextEditorPane extends JScrollPane implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private Font font;
    private File file;

    public TextEditorPane(File file) {
        super();
        this.file = file;
        init(); // Initialize components
        setup(); // Setup components
        addComponents(); // Add components to the container
    }

    private void addComponents() {
        super.setViewportView(textArea);
    }

    private void setup() {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(font);
    }

    private void init() {
        textArea = new JTextArea();
        font = new Font("Akaya Kanadaka", Font.PLAIN, 20);
    }

    public void setFile(File file) {
        this.file = file;
    }
}
