package com.anas.jsimpletexteditor.buttons.newtab;

import com.anas.jsimpletexteditor.TabbedPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTabHead extends JButton implements ActionListener {
    private final TabbedPane tabbedPane;
    public NewTabHead(Font font, TabbedPane tabbedPane) {
        super("+");
        this.tabbedPane = tabbedPane;
        super.setPreferredSize(new Dimension(20, 20));
        setUpButton(font);
        super.addActionListener(this);
    }

    private void setUpButton(Font font) {
        super.setHorizontalAlignment(JLabel.CENTER);
        super.setVerticalAlignment(JLabel.CENTER);
        super.setFont(font);
        super.setOpaque(false);
        super.setBorderPainted(true);
        super.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        super.setToolTipText("New Tab");
        setContentAreaFilled(true);
        setFocusable(false);
        setUI(new BasicButtonUI());
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        tabbedPane.openNewTab(null);
    }
}
