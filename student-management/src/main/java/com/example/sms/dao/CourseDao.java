package com.example.sms.dao;

import com.example.sms.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    int create(Course course);
    Optional<Course> findById(int courseId);
    List<Course> findAll();
    boolean update(Course course);
    boolean delete(int courseId);
}