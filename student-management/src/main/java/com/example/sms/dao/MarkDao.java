package com.example.sms.dao;

import com.example.sms.model.Mark;

import java.util.List;
import java.util.Optional;

public interface MarkDao {
    int create(Mark mark);
    Optional<Mark> findById(int markId);
    List<Mark> findByStudentId(int studentId);
    List<Mark> findByCourseId(int courseId);
    boolean update(Mark mark);
    boolean delete(int markId);
}