package com.anas.jsimpletexteditor.settings;

import java.io.File;

public class UISettings extends Settings {

    public UISettings() {
        super();
    }

    public UISettings(Settings uiSettings) {
        super(uiSettings);
    }

    @Override
    public String getSettingsFilePath() {
        return super.getBasePath() + File.separator + "ui.ser";
    }
}
