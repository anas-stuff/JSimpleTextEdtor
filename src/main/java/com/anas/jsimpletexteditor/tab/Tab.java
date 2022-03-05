package com.anas.jsimpletexteditor.tab;

import com.anas.jsimpletexteditor.TabbedPane;
import com.anas.jsimpletexteditor.TextEditorPane;
import com.anas.jsimpletexteditor.files.TextFile;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class Tab extends JPanel {
    private final TextEditorPane textEditorPane;
    private final TabHead tabHead;
    private TextFile textFile;

    public Tab(TabbedPane tabbedPane) {
        this(tabbedPane, null);
    }

    public Tab(TabbedPane tabbedPane, TextFile textFile) {
        super.setLayout(new MigLayout());
        this.textFile = Objects.requireNonNullElseGet(textFile, () -> new TextFile("Untitled"));
        textEditorPane = new TextEditorPane(this.textFile);
        super.add(textEditorPane, "grow, push");
        tabHead = new TabHead(tabbedPane, this);
    }

    @Override
    public String getName() {
        return textFile.getName();
    }

    public void setFile(TextFile textFile) {
        this.textFile = textFile;
        textEditorPane.setTextFile(Tab.this.textFile);
    }

    public boolean close() {
        return true;
    }

    public String getTitle() {
        return textFile.getName();
    }

    public TabHead getTabHead() {
        return tabHead;
    }

    public TextEditorPane getTextEditorPane() {
        return textEditorPane;
    }

    public void save(String path) {
        tabHead.setFileName(new File(path).getName());
        textEditorPane.save(path);
    }

    public boolean hasChange() {
        if (!textFile.exists())
            return true;
        return tabHead.hasChange();
    }

    public boolean exit() {
        if (hasChange()) {
            int result = JOptionPane.showConfirmDialog(this, "Do you want to save changes?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                return save();
            }
        }
        return true;
    }

    public boolean save() {
        if (!textFile.exists()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save location");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                textFile.setPath(fileChooser.getSelectedFile().getAbsolutePath());
            } else {
                return false;
            }
        }
        save(textFile.getPath());
        return true;
    }
}
