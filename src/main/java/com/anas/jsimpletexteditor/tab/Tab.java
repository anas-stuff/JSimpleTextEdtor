package com.anas.jsimpletexteditor.tab;

import com.anas.jsimpletexteditor.TabbedPane;
import com.anas.jsimpletexteditor.TextEditorPane;
import com.anas.jsimpletexteditor.buttons.closetab.CloseTabButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class Tab extends JPanel {
    private TextEditorPane textEditorPane;
    private TabHead tabHead;
    private File file;

    public Tab(TabbedPane tabbedPane) {
        this(tabbedPane, null);
    }

    public Tab(TabbedPane tabbedPane, File file) {
        super.setLayout(new MigLayout());
        this.file = Objects.requireNonNullElseGet(file, () -> new File("Untitled"));
        textEditorPane = new TextEditorPane(this.file);
        super.add(textEditorPane, "grow, push");
        tabHead = new TabHead(tabbedPane, this);
    }

    @Override
    public String getName() {
        return file.getName();
    }

    public void setFile(File file) {
        this.file = file;
        textEditorPane.setFile(file);
    }

    public boolean close() {
        return true;
    }

    public String getTitle() {
        return file.getName();
    }

    public TabHead getTabHead() {
        return tabHead;
    }
}
