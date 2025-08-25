package com.example.sms.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MarksPanel extends JPanel {
    public MarksPanel() {
        setLayout(new BorderLayout());

        JTable table = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mark ID", "Student ID", "Course ID", "Score"}
        ));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actions.add(new JButton("Add"));
        actions.add(new JButton("Edit"));
        actions.add(new JButton("Delete"));

        add(actions, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}