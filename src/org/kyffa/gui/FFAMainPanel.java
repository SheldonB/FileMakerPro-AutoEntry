package org.kyffa.gui;

import org.kyffa.general.Main;
import org.kyffa.models.Chapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FFAMainPanel extends JPanel {
    private JTextArea chapterNameArea;
    private JTextArea weekNumberArea;
    private JButton startButton;
    private FileChooserPanel chooserPanel;

    public FFAMainPanel() {
        this.setPreferredSize(new Dimension(400, 110));
        this.chapterNameArea = new JTextArea(1, 15);
        this.chapterNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.weekNumberArea = new JTextArea(1,1);
        this.weekNumberArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.startButton = new JButton("Start");
        this.chooserPanel = new FileChooserPanel();

        this.add(new JLabel("org.kyffa.models.Chapter Name: "));
        this.add(this.chapterNameArea);
        this.add(new JLabel("Week: "));
        this.add(this.weekNumberArea);
        this.add(this.chooserPanel);
        this.add(this.startButton);

        this.chapterNameArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    chapterNameArea.transferFocus();
                }
            }
        });

        this.startButton.addActionListener(e -> {
            if(e.getSource() == startButton) {
                Main.frameVisible(false);
                if(Main.run(new Chapter(chapterNameArea.getText(), Integer.parseInt(weekNumberArea.getText())),
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
