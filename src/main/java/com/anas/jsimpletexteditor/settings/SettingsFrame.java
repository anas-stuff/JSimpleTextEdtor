package com.anas.jsimpletexteditor.settings;

import com.anas.jsimpletexteditor.settings.categories.ColorCategory;
import com.anas.jsimpletexteditor.settings.categories.FontCategory;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {
    private JList<String> categoriesList;
    private JScrollPane categoriesScrollPane;
    private String[] categories;
    private JPanel settingsPanel;
    private JScrollPane settingsScrollPane;

    private JSplitPane splitPane;

    private JButton okButton, applyButton, cancelButton;

    public SettingsFrame() {
        super("Settings");
        setUpTheFrame();
        setUpTheCategories();
        setUpTheSettingsPanel();
        setUpTheSplitPane();
        setUpTheButtons();
        addTheComponents();
        super.setVisible(true);
    }

    private void setUpTheSplitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, categoriesScrollPane, settingsScrollPane);
        splitPane.setDividerLocation(14);
        splitPane.setOneTouchExpandable(true); // Add the fast resize feature
        splitPane.setResizeWeight(0.5);
        splitPane.setContinuousLayout(true); // this is needed to make the divider move smoothly
        splitPane.setPreferredSize(new Dimension(490, 450));
    }

    private void setUpTheButtons() {
        okButton = new JButton("OK");
        applyButton = new JButton("Apply");
        cancelButton = new JButton("Cancel");
    }

    private void addTheComponents() {
        add(splitPane, "grow, push, wrap");
        add(okButton, "split 3, grow, tag ok");
        add(applyButton, "grow, tag apply");
        add(cancelButton, "grow, tag cancel");
    }

    private void setUpTheSettingsPanel() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new MigLayout());
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        settingsPanel.add(new ColorCategory(), "grow, push, wrap");

        settingsScrollPane = new JScrollPane(settingsPanel);
    }

    private void setUpTheCategories() {
        categories = new String[3];
        categories[0] = new String("Font");
        categories[1] = new String("Theme");
        categories[2] = new String("Other");

        categoriesList = new JList<>(categories);
        categoriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoriesList.setLayoutOrientation(JList.VERTICAL_WRAP);
        categoriesList.setVisibleRowCount(-1);
        categoriesList.setSelectedIndex(0);

        categoriesScrollPane = new JScrollPane(categoriesList);
    }

    private void setUpTheFrame() {
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLayout(new MigLayout());
        super.setSize(new Dimension(500, 500));
        super.setLocationRelativeTo(null); // center the frame on the screen
    }

    // Test
    public static void main(String[] args) {
        new SettingsFrame();
    }

}
