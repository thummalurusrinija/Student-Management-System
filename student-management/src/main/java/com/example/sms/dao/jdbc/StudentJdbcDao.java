package com.example.sms.dao.jdbc;

import com.example.sms.config.ConnectionManager;
import com.example.sms.dao.StudentDao;
import com.example.sms.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentJdbcDao implements StudentDao {
    @Override
    public int create(Student student) {
        String sql = "INSERT INTO students (student_id, first_name, last_name, email, contact_no) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getContactNo());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert student", e);
        }
    }

    @Override
    public Optional<Student> findById(int studentId) {
        String sql = "SELECT student_id, first_name, last_name, email, contact_no FROM students WHERE student_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find student", e);
        }
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT student_id, first_name, last_name, email, contact_no FROM students ORDER BY student_id";
        List<Student> results = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list students", e);
        }
        return results;
    }

    @Override
    public boolean update(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ?, contact_no = ? WHERE student_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getContactNo());
            ps.setInt(5, student.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update student", e);
        }
    }

    @Override
    public boolean delete(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete student", e);
        }
    }

    @Override
    public List<Student> searchByNameOrEmail(String query) {
        String like = "%" + query + "%";
        String sql = "SELECT student_id, first_name, last_name, email, contact_no FROM students WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? ORDER BY last_name, first_name";
        List<Student> results = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to search students", e);
        }
        return results;
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setStudentId(rs.getInt("student_id"));
        s.setFirstName(rs.getString("first_name"));
        s.setLastName(rs.getString("last_name"));
        s.setEmail(rs.getString("email"));
        s.setContactNo(rs.getString("contact_no"));
        return s;
    }
}