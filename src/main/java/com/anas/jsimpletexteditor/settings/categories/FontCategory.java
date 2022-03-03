package com.anas.jsimpletexteditor.settings.categories;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class FontCategory extends AbstractSettingsCategory {
    private JPanel uiFontPanel, editorFontPanel;
    private JComboBox<String> uiFamilyComboBox, editorFamilyComboBox;
    private JSpinner uiSizeSpinner, editorSizeSpinner;
    private JComboBox<String> uiStyleComboBox, editorStyleComboBox;

    public FontCategory() {
        super("Font");
        addListeners();
        setUpPanels();
        addPanels();
    }

    private void addPanels() {
        super.add(uiFontPanel , "wrap, grow");
        super.add(editorFontPanel, "wrap");
    }

    private void setUpPanels() {
        uiFontPanel.setLayout(new MigLayout());
        uiFontPanel.setBorder(BorderFactory.createTitledBorder("UI"));

        editorFontPanel.setLayout(new MigLayout());
        editorFontPanel.setBorder(BorderFactory.createTitledBorder("Editor"));

        addComponentsToPanels();
    }

    private void addComponentsToPanels() {
        uiFontPanel.add(new JLabel("Family"));
        uiFontPanel.add(uiFamilyComboBox);
        uiFontPanel.add(new JLabel("Size"), "gap unrelated");
        uiFontPanel.add(uiSizeSpinner);
        uiFontPanel.add(new JLabel("Style"), "gap unrelated");
        uiFontPanel.add(uiStyleComboBox);

        editorFontPanel.add(new JLabel("Family"));
        editorFontPanel.add(editorFamilyComboBox);
        editorFontPanel.add(new JLabel("Size"), "gap unrelated");
        editorFontPanel.add(editorSizeSpinner);
        editorFontPanel.add(new JLabel("Style"), "gap unrelated");
        editorFontPanel.add(editorStyleComboBox);
    }

    private void addListeners() {
        addCompoBoxesListeners();
        addSpinnersListeners();
    }

    private void addSpinnersListeners() {
        uiSizeSpinner.addChangeListener(e -> super.uiSettings.setFontSize((int) uiSizeSpinner.getValue()));
        editorSizeSpinner.addChangeListener(e -> super.editorSettings.setFontSize((int) editorSizeSpinner.getValue()));
    }

    private void addCompoBoxesListeners() {
        uiFamilyComboBox.addActionListener(e -> super.uiSettings.setFontFamily(uiFamilyComboBox.getSelectedItem().toString()));
        editorFamilyComboBox.addActionListener(e -> super.editorSettings.setFontFamily(editorFamilyComboBox.getSelectedItem().toString()));

        uiStyleComboBox.addActionListener(e -> super.uiSettings.setFontStyle(uiStyleComboBox.getSelectedIndex()));
        editorStyleComboBox.addActionListener(e -> super.editorSettings.setFontStyle(editorStyleComboBox.getSelectedIndex()));
    }

    @Override
    protected void init() {
        initComboBoxes();
        initSpinners();
        initPanels();
    }

    @Override
    public void updateUi() {
        uiFamilyComboBox.setSelectedItem(super.uiSettings.getFont().getFamily());
        uiSizeSpinner.setValue(super.uiSettings.getFont().getSize());
        uiStyleComboBox.setSelectedItem(super.uiSettings.getFont().getStyle());

        editorFamilyComboBox.setSelectedItem(super.editorSettings.getFont().getFamily());
        editorSizeSpinner.setValue(super.editorSettings.getFont().getSize());
        editorStyleComboBox.setSelectedItem(super.editorSettings.getFont().getStyle());
    }

    private void initPanels() {
        uiFontPanel = new JPanel();
        editorFontPanel = new JPanel();
    }

    private void initSpinners() {
        SpinnerNumberModel model1 = new SpinnerNumberModel(12, 0, 100, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel((Number) model1.getValue(),
                model1.getMinimum(), model1.getMaximum(), model1.getStepSize());
        uiSizeSpinner = new JSpinner(model1);
        editorSizeSpinner = new JSpinner(model2);
    }

    private void initComboBoxes() {
        uiFamilyComboBox = new JComboBox<>();
        editorFamilyComboBox = new JComboBox<>();
        setUpFonts();
        uiStyleComboBox = new JComboBox<>();
        editorStyleComboBox = new JComboBox<>();
        setUpStyles();
    }

    private void setUpStyles() {
        String[] styles = {
                "Plain",
                "Bold",
                "Italic",
                "Bold Italic"
        };
        for (String style : styles) {
            uiStyleComboBox.addItem(style);
            editorStyleComboBox.addItem(style);
        }
    }

    private void setUpFonts() {
        String[] fonts =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); // get all fonts available on the system
        for (String font : fonts) {
            uiFamilyComboBox.addItem(font);
            editorFamilyComboBox.addItem(font);
        }
    }
}
