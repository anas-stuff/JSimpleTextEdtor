package com.anas.jsimpletexteditor.settings;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class Settings implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Font uiFont;
    private Color uiBackgroundColor, uiTextColor;

    private Font editorFont;
    private Color editorBackgroundColor, editorTextColor;

    public Settings() {
        uiFont = new Font("Arial", Font.PLAIN, 12);
        uiBackgroundColor = Color.WHITE;
        uiTextColor = Color.BLACK;

        editorFont = new Font("Arial", Font.PLAIN, 12);
        editorBackgroundColor = Color.WHITE;
        editorTextColor = Color.BLACK;
    }

    public Font getUiFont() {
        return uiFont;
    }

    public void setUiFont(Font uiFont) {
        this.uiFont = uiFont;
    }

    public Color getUiBackgroundColor() {
        return uiBackgroundColor;
    }

    public void setUiBackgroundColor(Color uiBackgroundColor) {
        this.uiBackgroundColor = uiBackgroundColor;
    }

    public Color getUiTextColor() {
        return uiTextColor;
    }

    public void setUiTextColor(Color uiTextColor) {
        this.uiTextColor = uiTextColor;
    }

    public Font getEditorFont() {
        return editorFont;
    }

    public void setEditorFont(Font editorFont) {
        this.editorFont = editorFont;
    }

    public Color getEditorBackgroundColor() {
        return editorBackgroundColor;
    }

    public void setEditorBackgroundColor(Color editorBackgroundColor) {
        this.editorBackgroundColor = editorBackgroundColor;
    }

    public Color getEditorTextColor() {
        return editorTextColor;
    }

    public void setEditorTextColor(Color editorTextColor) {
        this.editorTextColor = editorTextColor;
    }
}
