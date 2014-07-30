package org.kyffa.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class FileChooserPanel extends JPanel {
    private JTextArea fileNameArea;
    private JButton chooseFileButton;
    private JFileChooser fileChooser;
    private File excelDoc;

    public FileChooserPanel() {
        this.setPreferredSize(new Dimension(400, 45));
        this.fileNameArea = new JTextArea("No File Selected");
        this.fileNameArea.setColumns(10);
        this.fileNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.chooseFileButton = new JButton("Choose File");
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Documents", "xlsx"));

        this.chooseFileButton.addActionListener(e -> {
            if (e.getSource() == chooseFileButton) {
                int returnVal = fileChooser.showOpenDialog(FileChooserPanel.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    excelDoc = fileChooser.getSelectedFile();
                    fileNameArea.setText(excelDoc.getName());
                }
            }
        });

        this.add(this.fileNameArea);
        this.add(this.chooseFileButton);
    }

    public File getExcelDoc() {
        return this.excelDoc;
    }
}
