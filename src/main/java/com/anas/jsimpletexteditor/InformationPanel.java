package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.files.TextFile;
import com.anas.jsimpletexteditor.files.Type;
import com.anas.jsimpletexteditor.listners.TextEditorAreaListener;
import com.anas.jsimpletexteditor.tab.Tab;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InformationPanel extends JPanel implements ChangeListener {
    private JComboBox<Type> typeComboBox;
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
        add(charsNumberLabel, "");
        add(typeComboBox, "");
    }

    private void init() {
        typeComboBox = new JComboBox<>();
        charsNumberLabel = new JLabel("");
        Tab tab = tabbedPane.getCurrentTab();
        textEditorAreaListener.setInformationPanel(this);
        if (tab != null) {
            setValues(tab);
        } else {
            typeComboBox.removeAllItems();
        }
        tabbedPane.addChangeListener(this);
    }

    private void setValues(Tab tab) {
        addItemsToTypeComboBox();
        TextFile textFile = tab.getTextEditorPane().getTextFile();
        typeComboBox.setSelectedIndex(textFile.getType().ordinal());
        updateCharsNumber();
    }

    private void addItemsToTypeComboBox() {
        for (Type type : Type.values()) {
            typeComboBox.addItem(type);
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        Tab tab = tabbedPane.getCurrentTab();
        if (tab != null) {
            setValues(tab);
            textEditorAreaListener.setAcceptTab(tab);
            tab.getTextEditorPane().getTextArea().addKeyListener(textEditorAreaListener);
        } else {
            textEditorAreaListener.setAcceptTab(null);
            charsNumberLabel.setText("");
            typeComboBox.removeAllItems();
        }
    }

    public void updateCharsNumber() {
        charsNumberLabel.setText("Chars: " + tabbedPane.getCurrentTab().getTextEditorPane().getTextArea().getText().length() + 1);
    }
}
