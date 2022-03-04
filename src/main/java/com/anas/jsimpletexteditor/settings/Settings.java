package com.anas.jsimpletexteditor.settings;

import java.awt.*;
import java.io.File;
import java.io.Serial;
import java.io.Serializable;

public abstract class Settings implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Font font;
    private Color backgroundColor, textColor;

    public Settings() {
        font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        backgroundColor = Color.WHITE;
        textColor = Color.BLACK;
    }

    public Settings(Settings settings) {
        setFont(settings.getFont());
        setBackgroundColor(settings.getBackgroundColor());
        setTextColor(settings.getTextColor());
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setFontFamily(String fontFamily) {
        this.setFont(new Font(fontFamily, font.getStyle(), font.getSize()));
    }

    public void setFontSize(int fontSize) {
        this.setFont(new Font(font.getFamily(), font.getStyle(), fontSize));
    }

    public void setFontStyle(int fontStyle) {
        int style = switch (fontStyle) {
            case 1 -> Font.BOLD;
            case 2 -> Font.ITALIC;
            case 3 -> Font.BOLD + Font.ITALIC;
            default -> Font.PLAIN;
        };
        this.setFont(new Font(font.getFamily(), fontStyle, font.getSize()));
    }

    public abstract String getSettingsFilePath();

    public String getBasePath() {
        return System.getProperty("user.dir") + File.separator + ".jsimpletexteditor";
    }

}
