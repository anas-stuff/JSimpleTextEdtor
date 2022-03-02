package com.anas.jsimpletexteditor.listners;

import com.anas.jsimpletexteditor.InformationPanel;
import com.anas.jsimpletexteditor.tab.Tab;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextEditorAreaListener implements KeyListener {
    private InformationPanel  informationPanel;
    private Tab acceptTab;

    public TextEditorAreaListener() {
        informationPanel = null;
        acceptTab = null;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (informationPanel != null) {
            if (acceptTab != null && acceptTab.getTextEditorPane().getTextArea().equals(keyEvent.getSource())) {
                informationPanel.updateCharsNumber();
                acceptTab.getTabHead().contentChanged(); // Add *
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public void setInformationPanel(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
    }

    public void setAcceptTab(Tab acceptTab) {
        this.acceptTab = acceptTab;
    }
}
