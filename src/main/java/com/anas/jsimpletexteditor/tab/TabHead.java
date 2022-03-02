package com.anas.jsimpletexteditor.tab;

import com.anas.jsimpletexteditor.TabbedPane;
import com.anas.jsimpletexteditor.buttons.closetab.CloseTabButton;

import javax.swing.*;
import java.awt.*;

public class TabHead extends JPanel {
    private final JLabel label;
    private final CloseTabButton closeButton;
    private boolean fileChanged;

    public TabHead(final TabbedPane tabbedPane, final Tab tab) {
        super();
        fileChanged = false;
        this.label = new JLabel(tab.getTitle());
        this.closeButton = new CloseTabButton(tabbedPane, tab);
        super.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        super.setOpaque(false);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 6));
        super.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
        addComponents();
    }

    private void addComponents() {
        super.add(label);
        super.add(closeButton);
    }

    public void contentChanged() {
        if (!fileChanged) {
            label.setText(label.getText() + "*");
            fileChanged = true;
        }
    }

    public void setFileName(String name) {
        this.label.setText(name);
        fileChanged = false;
    }
}
