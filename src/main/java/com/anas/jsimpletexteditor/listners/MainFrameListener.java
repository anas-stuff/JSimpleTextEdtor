package com.anas.jsimpletexteditor.listners;

import com.anas.jsimpletexteditor.MainFrame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrameListener implements WindowListener {
    private MainFrame mainFrame;

    public MainFrameListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        mainFrame.exit();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
