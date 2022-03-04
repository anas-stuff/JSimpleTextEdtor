package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.files.TextFile;
import com.anas.jsimpletexteditor.files.FileType;
import com.anas.jsimpletexteditor.listners.TextEditorAreaListener;
import com.anas.jsimpletexteditor.tab.Tab;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InformationPanel extends JPanel implements ChangeListener {
    private JComboBox<FileType> typeComboBox;
    private JLabel charsNumberLabel;
    private final TabbedPane tabbedPane;
    private final TextEditorAreaListener textEditorAreaListener;

    public InformationPanel(TabbedPane tabbedPane) {
        super();
        textEditorAreaListener = new TextEditorAreaListener();
        super.setLayout(new MigLayout());
        this.tabbedPane = tabbedPane;
        init();
        addComponents();
    }

    private void addComponents() {
        add(charsNumberLabel, "grow, push");
        add(typeComboBox, "");
    }

    private void init() {
        typeComboBox = new JComboBox<>();
        charsNumberLabel = new JLabel("");
        Tab tab = tabbedPane.getCurrentTab();
        textEditorAreaListener.setInformationPanel(this);
        setupComponents(tab);
        tabbedPane.addChangeListener(this);
    }

    private boolean setupComponents(Tab tab) {
        if (tab != null) {
            setValues(tab);
            charsNumberLabel.setVisible(true);
            typeComboBox.setVisible(true);
            return true;
        } else {
            charsNumberLabel.setVisible(false);
            typeComboBox.setVisible(false);
            return false;
        }
    }

    private void setValues(Tab tab) {
        addItemsToTypeComboBox();
        TextFile textFile = tab.getTextEditorPane().getTextFile();
        typeComboBox.setSelectedIndex(textFile.getType().ordinal());
        updateCharsNumber();
    }

    private void addItemsToTypeComboBox() {
        for (FileType fileType : FileType.values()) {
            typeComboBox.addItem(fileType);
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        Tab tab = tabbedPane.getCurrentTab();
        if (setupComponents(tab)) {
            textEditorAreaListener.setAcceptTab(tab);
            tab.getTextEditorPane().getTextArea().addKeyListener(textEditorAreaListener);
        }
    }

    public void updateCharsNumber() {
        charsNumberLabel.setText("Chars: " + (tabbedPane.getCurrentTab().getTextEditorPane().getTextArea().getText().length()));
    }
}
