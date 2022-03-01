package com.anas.jsimpletexteditor.buttons.closetab;

import com.anas.jsimpletexteditor.tab.Tab;
import com.anas.jsimpletexteditor.TabbedPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class CloseTabButton extends JButton {
    private Dimension size;
    public CloseTabButton(TabbedPane tabbedPane, Tab tab) {
        super("x");
        size = new Dimension(15, 15);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBorder(BorderFactory.createEtchedBorder());
        setBorderPainted(true);
        setBackground(new Color(254, 42, 42));
        setOpaque(true);
        setForeground(Color.white);
        setToolTipText("Close this tab");
        setContentAreaFilled(true);
        setFocusable(false);
        setUI(new BasicButtonUI());
        addActionListener(new CloseTabButtonListener(tabbedPane, tab));
    }
}
