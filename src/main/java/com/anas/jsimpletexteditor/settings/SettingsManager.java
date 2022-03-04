package com.anas.jsimpletexteditor.settings;

import java.io.*;
import java.util.ArrayList;

public class SettingsManager {
    private static SettingsManager instance;
    private Settings uiSettings, editorSettings;
    private final ArrayList<SettingsListener> listeners;

    private SettingsManager() {
        uiSettings = new UISettings();
        editorSettings = new EditorSettings();
        listeners = new ArrayList<>();
        loadAllSettings();
    }

    private void loadAllSettings() {
        uiSettings = loadSettings(uiSettings);
        editorSettings = loadSettings(editorSettings);
    }

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public Settings loadSettings(Settings settings) {
        File settingsFile = new File(settings.getSettingsFilePath());
        Settings loadedSettings = null;
        if (!settingsFile.exists()) {
            makeSettingsFile();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(settings.getSettingsFilePath());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            loadedSettings = (Settings) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (loadedSettings == null)
            return settings;
        return loadedSettings;
    }

    private void makeSettingsFile() {
        File settingsFile = new File(uiSettings.getSettingsFilePath());
        if (!settingsFile.getParentFile().exists()) {
            settingsFile.getParentFile().mkdirs();
        }
        saveAll();
    }

    private void saveAll() {
        save(uiSettings);
        save(editorSettings);
    }

    private void save(Settings settings) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(settings.getSettingsFilePath());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(settings);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void applySettings() {
        this.saveAll();
        this.notifySettingsListeners();
    }

    public void addSettingsListener(SettingsListener settingsListener) {
        if (!listeners.contains(settingsListener)) {
            listeners.add(settingsListener);
        }
    }

    public void removeSettingsListener(SettingsListener settingsListener) {
        listeners.remove(settingsListener);
    }

    private void notifySettingsListeners() {
        for (SettingsListener listener : listeners) {
            listener.onSettingsChanged(new SettingsChangedEvent((UISettings) uiSettings, (EditorSettings) editorSettings));
        }
    }

    public void applySettings(Settings ... settings) {
        if (settings == null || settings.length == 0)
            return;

        for (Settings setting : settings) {
            if (setting instanceof UISettings) {
                this.uiSettings = (UISettings) setting;
            } else if (setting instanceof EditorSettings) {
                this.editorSettings = (EditorSettings) setting;
            }
        }
        this.applySettings();
    }

    public Settings getUiSettings() {
        return uiSettings;
    }

    public Settings getEditorSettings() {
        return editorSettings;
    }
}
