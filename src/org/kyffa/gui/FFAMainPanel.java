package org.kyffa.gui;

import org.kyffa.general.Main;
import org.kyffa.models.Chapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FFAMainPanel extends JPanel {
    private JTextField chapterNameArea;
    private JTextField weekNumberArea;
    private JButton startButton;
    private FileChooserPanel chooserPanel;
    private HelpPanel helpPanel;

    public FFAMainPanel() {
        this.setPreferredSize(new Dimension(400, 150));
        this.chapterNameArea = new JTextField(15);
        //this.chapterNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //this.chapterNameArea.setTabSize(0);
        this.weekNumberArea = new JTextField(1);
        //this.weekNumberArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //this.weekNumberArea.setTabSize(0);
        this.startButton = new JButton("Start");
        this.chooserPanel = new FileChooserPanel();
        this.helpPanel = new HelpPanel();

        this.add(new JLabel("Chapter Name: "));
        this.add(this.chapterNameArea);
        this.add(new JLabel("Week: "));
        this.add(this.weekNumberArea);
        this.add(this.chooserPanel);
        this.add(this.startButton);
        this.add(this.helpPanel);

        this.chapterNameArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    chapterNameArea.transferFocus();
                }
            }
        });

        this.startButton.addActionListener(e -> {
            if(e.getSource() == startButton && (chapterNameArea.getText().replaceAll("\\s+", "").equals("")
                    || weekNumberArea.getText().replaceAll("\\s+","").equals("") || !chooserPanel.getHasSelectedFile())) {
                JOptionPane.showMessageDialog(new JFrame("Form Not Filled Out Properly"), "Please fill in both the chapter, week, and select a file.");
            } else if(e.getSource() == startButton) {
                Main.frameVisible(false);
                if(Main.run(new Chapter(chapterNameArea.getText().trim(), Integer.parseInt(weekNumberArea.getText().trim())),
                        chooserPanel.getExcelDoc())) {
                    System.out.println("Run Finished");
                    Main.frameVisible(true);
                } else {
                    System.out.println("Run failed");
                }
            }
        });
    }
}
