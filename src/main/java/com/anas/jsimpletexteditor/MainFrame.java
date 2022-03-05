package com.anas.jsimpletexteditor;

import com.anas.jsimpletexteditor.files.FileType;
import com.anas.jsimpletexteditor.files.TextFile;
import com.anas.jsimpletexteditor.listners.MainFrameListener;
import com.anas.jsimpletexteditor.settings.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable, SettingsListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private TabbedPane tabbedPane;
    private InformationPanel informationPanel;
    private JMenuBar menuBar;

    public MainFrame() {
        super("Simple Text Editor");
        init();
        addMenuBar();
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.setLayout(new MigLayout());
        super.setSize(new Dimension(800, 600));
        this.addComponents(); // add components to the frame
        this.setUpSettings((UISettings) SettingsManager.getInstance().getUiSettings());
        super.setLocationRelativeTo(null);
        super.addWindowListener(new MainFrameListener(this));
        super.setVisible(true);
        SettingsManager.getInstance().addSettingsListener(this);
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

        fileMenu.setFont(SettingsManager.getInstance().getUiSettings().getFont());
        fileMenu.setMnemonic('F');
        menuBar.add(fileMenu);

        super.setJMenuBar(menuBar);

    }

    private void addActions(JMenuItem[] fileMenuItems) {
        JFileChooser fileChooser = new JFileChooser();
        fileMenuItems[0].addActionListener(event -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                tabbedPane.openNewTab(new TextFile(fileChooser.getSelectedFile().getPath()));
            }
        });

        fileMenuItems[1].addActionListener(event -> {
            if (tabbedPane.getCurrentTab() != null)
                tabbedPane.getCurrentTab().save();
        });

        fileMenuItems[2].addActionListener(event -> {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    int currentIndex = tabbedPane.getSelectedIndex();
                    tabbedPane.getCurrentTab().save(fileChooser.getSelectedFile().getPath());
                    tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(currentIndex - 1));
                } catch (NullPointerException ignored) {}
            }
        });

        fileMenuItems[3].addActionListener(event -> tabbedPane.remove(tabbedPane.getCurrentTab()));

        fileMenuItems[4].addActionListener(event -> new SettingsFrame());

        fileMenuItems[5].addActionListener(event -> this.exit());
    }

    public void exit() {
        if (tabbedPane.anyTabHasChanged()) {
            if (JOptionPane.showConfirmDialog(this,
                    "You have unsaved changes. Are you sure you want to exit?",
                    "Exit", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
        }
        System.exit(0);
    }

    @Override
    public void onSettingsChanged(SettingsChangedEvent event) {
        setUpSettings(event.getUiSettings());
        super.revalidate();
        super.repaint();
    }

    private void setUpSettings(UISettings uiSettings) {
        super.setFont(uiSettings.getFont());
        super.setBackground(uiSettings.getBackgroundColor());
        super.getContentPane().setBackground(uiSettings.getBackgroundColor());
        super.setForeground(uiSettings.getTextColor());

        menuBar.setFont(uiSettings.getFont());
        menuBar.setBackground(uiSettings.getBackgroundColor());
        menuBar.setForeground(uiSettings.getTextColor());

        this.informationPanel.setBackground(uiSettings.getBackgroundColor());
        this.informationPanel.setForeground(uiSettings.getTextColor());
        this.informationPanel.setFont(uiSettings.getFont());
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
            item.setFont(SettingsManager.getInstance().getUiSettings().getFont());
        }
    }

    private void addComponents() {
        super.add(tabbedPane, "grow, push, wrap");
        super.add(informationPanel, "grow");
    }

    private void init() {
        tabbedPane = new TabbedPane();
        informationPanel = new InformationPanel(tabbedPane);
    }
}
