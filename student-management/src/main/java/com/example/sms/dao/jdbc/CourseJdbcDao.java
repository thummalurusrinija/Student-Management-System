package com.example.sms.dao.jdbc;

import com.example.sms.config.ConnectionManager;
import com.example.sms.dao.CourseDao;
import com.example.sms.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseJdbcDao implements CourseDao {
    @Override
    public int create(Course course) {
        String sql = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, course.getCourseId());
            ps.setString(2, course.getCourseName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert course", e);
        }
    }

    @Override
    public Optional<Course> findById(int courseId) {
        String sql = "SELECT course_id, course_name FROM courses WHERE course_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find course", e);
        }
    }

    @Override
    public List<Course> findAll() {
        String sql = "SELECT course_id, course_name FROM courses ORDER BY course_id";
        List<Course> results = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list courses", e);
        }
        return results;
    }

    @Override
    public boolean update(Course course) {
        String sql = "UPDATE courses SET course_name = ? WHERE course_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCourseId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update course", e);
        }
    }

    @Override
    public boolean delete(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete course", e);
        }
    }

    private Course mapRow(ResultSet rs) throws SQLException {
        return new Course(rs.getInt("course_id"), rs.getString("course_name"));
    }
}