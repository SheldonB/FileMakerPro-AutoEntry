package org.kyffa.gui;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
    private JButton helpButton;
    private JButton bugReportButton;

    public HelpPanel() {
        this.setPreferredSize(new Dimension(400, 50));
        helpButton = new JButton("Help");
        bugReportButton = new JButton("Report Bug");

        this.add(this.helpButton);
        this.add(this.bugReportButton);

        this.helpButton.addActionListener(e -> {
            if(e.getSource() == helpButton) {
            JOptionPane.showMessageDialog(new JFrame(), "Welcome to the KY FFA LTC FileMaker Pro Bot. Make\n" +
                                                        " sure all special interest forms have been filled\n" +
                                                        " in the excel document correctly. The AM Special interest\n" +
                                                        " should be in bold and the PM special interest class\n" +
                                                        " should be in italics. To compensate for having two \n" +
                                                        " communication classes, when putting a student in communication\n" +
                                                        " skills B underline that cell. This allows the program to know\n" +
                                                        " that this is different from communication skills A.\n" +
                                                        " Type in the name of the chapter\n" +
                                                        " and the week they are attending. Then go find the excel \n" +
                                                        " document with their information." + " Everything will be\n" +
                                                        " added into the system automatically. \n\n" +
                                                        " For questions and Bug Reports please use the bug report\n" +
                                                        " button which will be sent to Sheldon Burks.");
            }
        });

        this.bugReportButton.addActionListener(e -> {
            if(e.getSource() == bugReportButton) {
                JOptionPane.showMessageDialog(new JFrame(), "Please Email sheldon.burks@gmail.com with the bug\n" +
                                                            " report. Please in the message explain what you were\n" +
                                                            " doing and what went wrong.");
            }
        });

    }
}
