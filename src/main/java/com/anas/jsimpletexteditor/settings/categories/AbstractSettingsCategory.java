package com.anas.jsimpletexteditor.settings.categories;

import com.anas.jsimpletexteditor.settings.EditorSettings;
import com.anas.jsimpletexteditor.settings.Settings;
import com.anas.jsimpletexteditor.settings.SettingsManager;
import com.anas.jsimpletexteditor.settings.UISettings;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public abstract class AbstractSettingsCategory extends JPanel {
    private final SettingsManager settingsManager;
    protected Settings uiSettings, editorSettings;

    public AbstractSettingsCategory(String name) {
        super.setName(name);
        super.setLayout(new MigLayout());
        this.settingsManager = SettingsManager.getInstance();
        this.uiSettings = new UISettings(settingsManager.getUiSettings());
        this.editorSettings = new EditorSettings(settingsManager.getEditorSettings());
        this.init();
        this.updateUi();
    }

    public SettingsManager getSettingsManager() {
        return this.settingsManager;
    }

    public abstract void updateUi();

    public void saveSettings() {
        settingsManager.applySettings(uiSettings, editorSettings);
    }

    public void resetSettings() {
        uiSettings = new UISettings();
        editorSettings = new EditorSettings();
        this.updateUi();
    }

    protected abstract void init();
}
