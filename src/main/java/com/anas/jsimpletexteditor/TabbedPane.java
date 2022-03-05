package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.buttons.newtab.NewTabButton;
import com.anas.jsimpletexteditor.files.TextFile;
import com.anas.jsimpletexteditor.settings.SettingsManager;
import com.anas.jsimpletexteditor.tab.Tab;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class TabbedPane extends JTabbedPane {
    @Serial
    private static final long serialVersionUID = 1L;

    public TabbedPane() {
        super();
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.addNewTabButton();
    }

    public void openNewTab(TextFile textFile) {
        Tab tab = new Tab(this, textFile);
        super.insertTab(tab.getName(), null, tab, "", super.getTabCount() - 1); // 1 for the new tab
        super.setSelectedComponent(tab);
        super.setTabComponentAt(super.indexOfComponent(tab), tab.getTabHead());
    }

    private void addNewTabButton() {
        NewTabButton newTabButton = new NewTabButton(SettingsManager.getInstance().getUiSettings().getFont(), this);
        super.addTab("", newTabButton);
        super.setTabComponentAt(super.getTabCount() - 1, newTabButton.getNewTabHead());
    }

    public Tab getCurrentTab() {
        if (super.getSelectedIndex() == super.getTabCount() - 1)
            return null;
        return (Tab) super.getSelectedComponent();
    }

    @Override
    public void remove(Component component) {
        if (((Tab) component).exit()) {
            super.remove(component);
        }
    }

    public boolean anyTabHasChanged() {
        for (int i = 0; i < super.getTabCount() - 1; i++) {
            if (((Tab) super.getComponentAt(i)).hasChange()) {
                return true;
            }
        }
        return false;
    }
}
