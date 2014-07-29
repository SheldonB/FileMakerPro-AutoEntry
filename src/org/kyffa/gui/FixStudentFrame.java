package org.kyffa.gui;

import org.kyffa.models.Student;

import javax.swing.*;
import java.awt.*;

public class FixStudentFrame extends JFrame {

    public FixStudentFrame(Student errorStudent) {
        super("Special Interest Error");
        this.setPreferredSize(new Dimension(600, 100));
        this.getContentPane().add(new FixStudentPanel(errorStudent));
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}
