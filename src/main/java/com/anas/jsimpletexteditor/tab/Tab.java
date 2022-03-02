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
}
