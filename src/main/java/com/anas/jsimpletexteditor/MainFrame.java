package com.anas.jsimpletexteditor;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private TabbedPane tabbedPane;
    private InformationPanel informationPanel;
    private JMenuBar menuBar;
    private Font uiFont;
    private Color backgroundColor, fontColor;
    public MainFrame() {
        super("Simple Text Editor");
        init();
        addMenuBar();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.setPreferredSize(sizeDimension);
        super.setLayout(new MigLayout());
        this.addComponents(); // add components to the frame
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    private void addMenuBar() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu newFileMenu = new JMenu("New File");
        JMenuItem[] fileMenuItems = {
                        new JMenuItem("Open"),
                        new JMenuItem("Save"), new JMenuItem("Save As"),
                        new JMenuItem("Close File"), new JMenuItem("Settings"),
                        new JMenuItem("Exit")
        };

        fileMenu.add(newFileMenu);
        newFileMenu.setMnemonic('N');
        for (JMenuItem item : fileMenuItems) {
            fileMenu.add(item);
            item.setMnemonic(item.getText().charAt(0));
            if (item.getText().equals("Save As")) {
                item.setMnemonic('A');
            }
        }

        addFileMenuNewFileTypeItems(newFileMenu);

        fileMenu.setFont(uiFont);
        fileMenu.setMnemonic('F');
        menuBar.add(fileMenu);

        super.setJMenuBar(menuBar);

    }

    private void addFileMenuNewFileTypeItems(JMenu newFileMenu) {
        JMenuItem[] newFileMenuItems = {
                        new JMenuItem("Plain file"),
                        new JMenuItem("Text File"), new JMenuItem("HTML File"),
                        new JMenuItem("Java File"), new JMenuItem("Python File"),
                        new JMenuItem("C File"), new JMenuItem("C++ File"),
                        new JMenuItem("JavaScript File"), new JMenuItem("CSS File")
        };
        for (JMenuItem item : newFileMenuItems) {
            newFileMenu.add(item);
            item.addActionListener(event -> {
                tabbedPane.openNewTab(null);
            });
            item.setFont(uiFont);
        }
    }

    private void addComponents() {
        super.add(tabbedPane, "grow, push, wrap");
        super.add(informationPanel, "grow");
    }

    private void init() {
        tabbedPane = new TabbedPane(uiFont);
        informationPanel = new InformationPanel(tabbedPane);
    }
}
