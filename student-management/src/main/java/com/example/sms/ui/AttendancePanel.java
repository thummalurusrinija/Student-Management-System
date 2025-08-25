package com.example.sms.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AttendancePanel extends JPanel {
    public AttendancePanel() {
        setLayout(new BorderLayout());

        JPanel filter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filter.add(new JLabel("Date (YYYY-MM-DD):"));
        JTextField dateField = new JTextField(10);
        JButton loadButton = new JButton("Load");
        filter.add(dateField);
        filter.add(loadButton);

        JTable table = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Attendance ID", "Student ID", "Date", "Status"}
        ));

        add(filter, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}