package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.tab.Tab;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serial;

public class TabbedPane extends JTabbedPane {
    @Serial
    private static final long serialVersionUID = 1L;

    public TabbedPane(Font uiFont) {
        super();
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void openNewTab(File file) {
        Tab tab = new Tab(this, file);
        super.addTab(tab.getName(), tab);
        super.setSelectedComponent(tab);
        super.setTabComponentAt(super.indexOfComponent(tab), tab.getTabHead());
    }
}
