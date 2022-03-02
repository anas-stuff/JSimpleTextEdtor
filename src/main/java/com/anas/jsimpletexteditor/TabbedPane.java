package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.buttons.newtab.NewTabButton;
import com.anas.jsimpletexteditor.files.TextFile;
import com.anas.jsimpletexteditor.tab.Tab;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serial;

public class TabbedPane extends JTabbedPane {
    @Serial
    private static final long serialVersionUID = 1L;
    transient private Font uiFont;

    public TabbedPane(Font uiFont) {
        super();
        this.uiFont = uiFont;
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.addNewTabButton();
    }

    public void openNewTab(File file) {
        Tab tab = new Tab(this, (TextFile) file);
        super.insertTab(tab.getName(), null, tab, "", super.getTabCount() - 1); // 1 for the new tab
        super.setSelectedComponent(tab);
        super.setTabComponentAt(super.indexOfComponent(tab), tab.getTabHead());
    }

    private void addNewTabButton() {
        NewTabButton newTabButton = new NewTabButton(uiFont, this);
        super.addTab("", newTabButton);
        super.setTabComponentAt(super.getTabCount() - 1, newTabButton.getNewTabHead());
    }

    public Tab getCurrentTab() {
        if (super.getSelectedIndex() == super.getTabCount() - 1)
            return null;
        return (Tab) super.getSelectedComponent();
    }
}
