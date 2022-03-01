package com.anas.jsimpletexteditor.buttons.closetab;

import com.anas.jsimpletexteditor.tab.Tab;
import com.anas.jsimpletexteditor.TabbedPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseTabButtonListener implements ActionListener {
    private final TabbedPane tabbedPane;
    private final Tab tab;
    protected CloseTabButtonListener(TabbedPane tabbedPane, Tab tab) {
        this.tabbedPane = tabbedPane;
        this.tab = tab;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (tab.close()) {
            tabbedPane.remove(tab);
        }
    }
}
