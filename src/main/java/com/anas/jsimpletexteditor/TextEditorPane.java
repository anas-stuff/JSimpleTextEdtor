package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.files.TextFile;
import com.anas.jsimpletexteditor.settings.EditorSettings;
import com.anas.jsimpletexteditor.settings.SettingsChangedEvent;
import com.anas.jsimpletexteditor.settings.SettingsListener;
import com.anas.jsimpletexteditor.settings.SettingsManager;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class TextEditorPane extends JScrollPane implements Serializable, SettingsListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
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
        setUpThePopupMenu();
        this.setUpUI((EditorSettings) SettingsManager.getInstance().getEditorSettings());
    }

    private void setUpThePopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        popupMenu.add(new JMenuItem("Cut")).addActionListener(e -> textArea.cut());
        popupMenu.add(new JMenuItem("Copy")).addActionListener(e -> textArea.copy());
        popupMenu.add(new JMenuItem("Paste")).addActionListener(e -> textArea.paste());
        popupMenu.addSeparator();
        popupMenu.add(new JMenuItem("Select All")).addActionListener(e -> textArea.selectAll());
        popupMenu.addSeparator();
        popupMenu.add(new JMenuItem("Find"))
                .addActionListener(e -> JOptionPane.showMessageDialog(null, "Sooooon :)")); // TODO: Implement find

        textArea.setComponentPopupMenu(popupMenu);
    }

    private void init() {
        textArea = new JTextArea();
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
        return textFile.save(path, textArea.getText());
    }

    @Override
    public void onSettingsChanged(SettingsChangedEvent event) {
        setUpUI(event.getEditorSettings());
        textArea.revalidate();
        textArea.repaint();
    }

    private void setUpUI(EditorSettings settings) {
        textArea.setFont(settings.getFont());
        textArea.setForeground(settings.getTextColor());
        textArea.setBackground(settings.getBackgroundColor());
        textArea.setCaretColor(settings.getTextColor());
    }
}
