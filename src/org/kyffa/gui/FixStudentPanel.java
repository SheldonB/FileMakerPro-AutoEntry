package org.kyffa.gui;

import org.kyffa.general.Main;
import org.kyffa.models.Student;

import javax.swing.*;

public class FixStudentPanel extends JPanel {
    private JComboBox<String> amSpecialInterestDropdown;
    private JComboBox<String> pmSpecialInterestDropdown;
    private JButton submitButton;
    private Student errorStudent;

    public FixStudentPanel(Student errorStudent) {
        this.errorStudent = errorStudent;
        this.amSpecialInterestDropdown = new JComboBox<>(Main.specialInterestClasses);
        this.pmSpecialInterestDropdown = new JComboBox<>(Main.specialInterestClasses);
        this.submitButton = new JButton("Submit");
        this.add(new JLabel("There was an error in " + this.errorStudent.getFirstName()
                + " " + this.errorStudent.getLastName() + "'s form. \nPlease re-input the special interest classes."));
        this.add(this.amSpecialInterestDropdown);
        this.add(this.pmSpecialInterestDropdown);
        this.add(this.submitButton);

        this.submitButton.addActionListener(e -> {
            if(e.getSource() == submitButton) {
                errorStudent.setSpecialInterestAM((String)amSpecialInterestDropdown.getSelectedItem());
                errorStudent.setSpecialInterestPM((String)pmSpecialInterestDropdown.getSelectedItem());
            }
        });
    }
}
