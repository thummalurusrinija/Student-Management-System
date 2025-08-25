package com.example.sms.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CoursesPanel extends JPanel {
    public CoursesPanel() {
        setLayout(new BorderLayout());

        JTable table = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Course Name"}
        ));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actions.add(new JButton("Add"));
        actions.add(new JButton("Edit"));
        actions.add(new JButton("Delete"));

        add(actions, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}