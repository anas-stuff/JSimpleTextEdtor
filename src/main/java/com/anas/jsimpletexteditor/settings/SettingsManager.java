package com.anas.jsimpletexteditor.settings;

import java.io.*;
import java.util.ArrayList;

public class SettingsManager {
    private static SettingsManager instance;
    private Settings settings;
    private final String settingsFilePath;
    private final ArrayList<SettingsListener> listeners;

    private SettingsManager() {
        settings = new Settings();
        settingsFilePath = System.getProperty("user.home") + File.separator + ".jsimpletexteditor" + File.separator + "settings.ser";
        listeners = new ArrayList<>();
        loadSettings();
    }

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public Settings getSettings() {
        return settings;
    }

    public void loadSettings() {
        File settingsFile = new File(settingsFilePath);
        if (!settingsFile.exists()) {
            makeSettingsFile();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(settingsFilePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            settings = (Settings) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void makeSettingsFile() {
        File settingsFile = new File(settingsFilePath);
        if (!settingsFile.getParentFile().exists()) {
            settingsFile.getParentFile().mkdirs();
        }
        saveSettings();
    }

    private void saveSettings() {
        settings = new Settings(); // Default settings
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(settingsFilePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(settings);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void applySettings() {
        this.saveSettings();
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
            listener.onSettingsChanged(settings);
        }
    }

    public void applySettings(Settings settings) {
        this.settings = settings;
        this.applySettings();
    }
}
