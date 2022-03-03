package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.files.TextFile;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class TextEditorPane extends JScrollPane implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private Font font;
    private TextFile textFile;

    public TextEditorPane(TextFile textFile) {
        super();
        this.textFile = textFile;
        init(); // Initialize components
        setup(); // Setup components
        addComponents(); // Add components to the container
        refresh();
    }

    private void addComponents() {
        super.setViewportView(textArea);
    }

    private void setup() {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(font);
        setUpThePopupMenu();
    }

    private void setUpThePopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        popupMenu.add(new JMenuItem("Cut")).addActionListener(e -> textArea.cut());
        popupMenu.add(new JMenuItem("Copy")).addActionListener(e -> textArea.copy());
        popupMenu.add(new JMenuItem("Paste")).addActionListener(e -> textArea.paste());
        popupMenu.addSeparator();
        popupMenu.add(new JMenuItem("Select All")).addActionListener(e -> textArea.selectAll());
        popupMenu.addSeparator();
        popupMenu.add(new JMenuItem("Find")); // TODO: Implement find

        textArea.setComponentPopupMenu(popupMenu);
    }

    private void init() {
        textArea = new JTextArea();
        font = new Font("", Font.PLAIN, 20);
    }

    public void setTextFile(TextFile textFile) {
        this.textFile = textFile;
    }

    public TextFile getTextFile() {
        return textFile;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void refresh() {
        textArea.setText(textFile.getText());
    }

    public boolean save(String path) {

        return  textFile.save(path, textArea.getText());
    }
}
