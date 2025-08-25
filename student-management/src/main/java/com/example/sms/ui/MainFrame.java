package com.example.sms.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Students", new StudentsPanel());
        tabs.addTab("Courses", new CoursesPanel());
        tabs.addTab("Marks", new MarksPanel());
        tabs.addTab("Attendance", new AttendancePanel());

        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
    }

    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new MainFrame().setVisible(true);
        });
    }
}