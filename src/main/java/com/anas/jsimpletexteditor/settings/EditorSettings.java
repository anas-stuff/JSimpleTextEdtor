package com.anas.jsimpletexteditor.settings;

import java.io.File;

public class EditorSettings extends Settings {

    public EditorSettings() {
        super();
    }

    public EditorSettings(Settings editorSettings) {
        super(editorSettings);
    }

    @Override
    public String getSettingsFilePath() {
        return super.getBasePath() + File.separator + "editor.ser";
    }
}
