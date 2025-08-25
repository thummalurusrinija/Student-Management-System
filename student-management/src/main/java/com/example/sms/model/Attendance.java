package com.example.sms.model;

import java.time.LocalDate;

public class Attendance {
    private int attendanceId;
    private int studentId;
    private LocalDate date;
    private Status status;

    public enum Status {
        Present,
        Absent
    }

    public Attendance() {
    }

    public Attendance(int attendanceId, int studentId, LocalDate date, Status status) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}