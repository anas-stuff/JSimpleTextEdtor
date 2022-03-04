package com.anas.jsimpletexteditor.settings;

public class SettingsChangedEvent {
    private final UISettings uiSettings;
    private final EditorSettings editorSettings;

    public SettingsChangedEvent(UISettings uiSettings, EditorSettings editorSettings) {
        this.uiSettings = uiSettings;
        this.editorSettings = editorSettings;
    }

    public UISettings getUiSettings() {
        return uiSettings;
    }

    public EditorSettings getEditorSettings() {
        return editorSettings;
    }
}
