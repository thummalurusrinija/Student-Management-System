package com.example.sms.dao;

import com.example.sms.model.Attendance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceDao {
    int create(Attendance attendance);
    Optional<Attendance> findById(int attendanceId);
    List<Attendance> findByStudentId(int studentId);
    List<Attendance> findByDate(LocalDate date);
    boolean update(Attendance attendance);
    boolean delete(int attendanceId);
}