package com.example.sms.dao;

import com.example.sms.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    int create(Student student);
    Optional<Student> findById(int studentId);
    List<Student> findAll();
    boolean update(Student student);
    boolean delete(int studentId);
    List<Student> searchByNameOrEmail(String query);
}