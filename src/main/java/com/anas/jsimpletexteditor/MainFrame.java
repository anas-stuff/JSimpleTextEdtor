package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.files.FileType;
import com.anas.jsimpletexteditor.files.TextFile;
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

        addActions(fileMenuItems);

        addFileMenuNewFileTypeItems(newFileMenu);

        fileMenu.setFont(uiFont);
        fileMenu.setMnemonic('F');
        menuBar.add(fileMenu);

        super.setJMenuBar(menuBar);

    }

    private void addActions(JMenuItem[] fileMenuItems) {
        fileMenuItems[0].addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                tabbedPane.openNewTab(new TextFile(fileChooser.getSelectedFile().getPath()));
            }
        });
    }

    private static class CustomMenuItem extends JMenuItem {
        FileType fileType;
        CustomMenuItem(FileType fileType) {
            super(fileType.toString());
            this.fileType = fileType;
        }
    }

    private void addFileMenuNewFileTypeItems(JMenu newFileMenu) {
        CustomMenuItem[] newFileMenuItems = new CustomMenuItem[FileType.values().length];
        // Fill
        for (FileType fileType : FileType.values()) {
            newFileMenuItems[fileType.ordinal()] = new CustomMenuItem(fileType);
        }
        for (CustomMenuItem item : newFileMenuItems) {
            newFileMenu.add(item);
            item.addActionListener(event -> {
                TextFile textFile = new TextFile("Untitled." + item.fileType.getExtension());
                textFile.setType(item.fileType);
                tabbedPane.openNewTab(textFile);
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
