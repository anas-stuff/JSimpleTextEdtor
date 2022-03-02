package com.anas.jsimpletexteditor.buttons.newtab;

import com.anas.jsimpletexteditor.TabbedPane;

import javax.swing.*;
import java.awt.*;

public class NewTabButton extends JPanel {
    private final NewTabHead newTabHead;

    public NewTabButton(final Font font, final TabbedPane tabbedPane) {
        newTabHead = new NewTabHead(font, tabbedPane);
    }

    public NewTabHead getNewTabHead() {
        return newTabHead;
    }
}
