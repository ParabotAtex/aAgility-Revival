package org.parabot.scriptwriter.revival.aAgility.ui;

import org.parabot.scriptwriter.revival.aAgility.data.Course;
import org.parabot.scriptwriter.revival.aAgility.data.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel contentPane;
    private JComboBox cmbCourse;
    private JButton startButton;
    private Settings settings;

    public GUI() {
        setTitle("Configure whitelist");
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        for(Course course : Course.values()) {
            cmbCourse.addItem(course);
        }
        cmbCourse.setSelectedIndex(0);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cmbCourse.getSelectedIndex() != -1) {
                    for(Course c : Course.values()) {
                        if(c.equals(cmbCourse.getSelectedItem())) {
                            settings = new Settings(c);
                            dispose();
                        }
                    }
                }
            }
        });
    }

    public Settings getSettings() {
        return settings;
    }
}
