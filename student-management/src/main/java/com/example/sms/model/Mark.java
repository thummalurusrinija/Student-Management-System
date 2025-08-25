package com.example.sms.model;

public class Mark {
    private int markId;
    private int studentId;
    private int courseId;
    private double score;

    public Mark() {
    }

    public Mark(int markId, int studentId, int courseId, double score) {
        this.markId = markId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}