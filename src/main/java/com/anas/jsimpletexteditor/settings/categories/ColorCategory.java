package com.anas.jsimpletexteditor.settings.categories;

import javax.swing.*;

public class ColorCategory extends AbstractSettingsCategory {
    private JColorChooser uiFontColorChooser, uiBackgroundColorChooser;
    private JColorChooser editorFontColorChooser, editorBackgroundColorChooser;
    private static AbstractSettingsCategory instance;

    private ColorCategory() {
        super("Color");
    }

    public static AbstractSettingsCategory getInstance() {
        if (instance == null) {
            instance = new ColorCategory();
        }
        return instance;
    }

    public static void removeInstance() {
        instance = null;
    }

    @Override
    protected void init() {
        uiFontColorChooser = new JColorChooser();
        uiBackgroundColorChooser = new JColorChooser();
        editorFontColorChooser = new JColorChooser();
        editorBackgroundColorChooser = new JColorChooser();
    }

    @Override
    protected void addComponentsToPanels() {
        JPanel uiFontColorPanel = new JPanel();
        uiFontColorPanel.setBorder(BorderFactory.createTitledBorder("Text Color"));
        uiFontColorPanel.add(uiFontColorChooser);
        super.getUiPanel().add(uiFontColorPanel, "wrap");

        JPanel uiBackgroundColorPanel = new JPanel();
        uiBackgroundColorPanel.setBorder(BorderFactory.createTitledBorder("Background Color"));
        uiBackgroundColorPanel.add(uiBackgroundColorChooser);
        super.getUiPanel().add(uiBackgroundColorPanel, "wrap");

        JPanel editorFontColorPanel = new JPanel();
        editorFontColorPanel.setBorder(BorderFactory.createTitledBorder("Text Color"));
        editorFontColorPanel.add(editorFontColorChooser);
        super.getEditorPanel().add(editorFontColorPanel, "wrap");

        JPanel editorBackgroundColorPanel = new JPanel();
        editorBackgroundColorPanel.setBorder(BorderFactory.createTitledBorder("Background Color"));
        editorBackgroundColorPanel.add(editorBackgroundColorChooser);
        super.getEditorPanel().add(editorBackgroundColorPanel, "wrap");
    }

    @Override
    protected void addListeners() {
        uiFontColorChooser.getSelectionModel().addChangeListener(e -> super.uiSettings.setTextColor(uiFontColorChooser.getColor()));

        uiBackgroundColorChooser.getSelectionModel().addChangeListener(e -> super.uiSettings.setBackgroundColor(uiBackgroundColorChooser.getColor()));

        editorFontColorChooser.getSelectionModel().addChangeListener(e -> super.editorSettings.setTextColor(editorFontColorChooser.getColor()));

        editorBackgroundColorChooser.getSelectionModel().addChangeListener(e -> super.editorSettings.setBackgroundColor(editorBackgroundColorChooser.getColor()));
    }

    @Override
    public void updateUi() {
        uiFontColorChooser.setColor(super.uiSettings.getTextColor());
        uiBackgroundColorChooser.setColor(super.uiSettings.getBackgroundColor());

        editorFontColorChooser.setColor(super.editorSettings.getTextColor());
        editorBackgroundColorChooser.setColor(super.editorSettings.getBackgroundColor());
    }
}
