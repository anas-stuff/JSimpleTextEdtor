package com.anas.jsimpletexteditor.settings.categories;

import com.anas.jsimpletexteditor.settings.Settings;
import com.anas.jsimpletexteditor.settings.SettingsManager;

import javax.swing.*;

public abstract class AbstractSettingsCategory extends JPanel {
    private final SettingsManager settingsManager;
    private final Settings settings;

    public AbstractSettingsCategory(String name) {
        super.setName(name);
        this.settingsManager = SettingsManager.getInstance();
        this.settings = settingsManager.getSettings();
    }

    public SettingsManager getSettingsManager() {
        return this.settingsManager;
    }

    public abstract void loadSettings();

    public void saveSettings() {
        settingsManager.applySettings(settings);
    }

    public abstract void resetSettings();

    public abstract void applySettings();
}
