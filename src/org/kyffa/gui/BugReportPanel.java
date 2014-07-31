package org.kyffa.gui;

import org.kyffa.general.SendEmail;

import javax.swing.*;
import java.awt.*;


public class BugReportPanel extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextArea bugField;
    private JButton submitButton;
    public BugReportPanel() {
        this.setPreferredSize(new Dimension(250, 300));
        JLabel nameLabel = new JLabel("Name:");
        this.nameField = new JTextField(15);
        this.add(nameLabel);
        this.add(this.nameField);

        JLabel emailLabel = new JLabel("Email:");
        this.emailField = new JTextField(15);
        this.add(emailLabel);
        this.add(this.emailField);

        JLabel bugFieldLabel = new JLabel("Please Enter what went wrong: ");
        this.bugField = new JTextArea(10, 20);
        this.bugField.setLineWrap(true);
        this.bugField.setWrapStyleWord(true);
        this.bugField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(bugFieldLabel);
        this.add(this.bugField);

        this.submitButton = new JButton("Submit");
        this.add(submitButton);

        this.submitButton.addActionListener(e -> {
            if(e.getSource() == submitButton) {

            }
        });
    }

    public String getName() {
        return this.nameField.getText();
    }

    public String getEmail() {
        return this.emailField.getText();
    }

    public String getBugText() {
        return this.bugField.getText();
    }
}
