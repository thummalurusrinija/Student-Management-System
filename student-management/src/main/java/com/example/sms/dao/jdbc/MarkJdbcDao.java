package com.example.sms.dao.jdbc;

import com.example.sms.config.ConnectionManager;
import com.example.sms.dao.MarkDao;
import com.example.sms.model.Mark;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MarkJdbcDao implements MarkDao {
    @Override
    public int create(Mark mark) {
        String sql = "INSERT INTO marks (mark_id, student_id, course_id, score) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mark.getMarkId());
            ps.setInt(2, mark.getStudentId());
            ps.setInt(3, mark.getCourseId());
            ps.setDouble(4, mark.getScore());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert mark", e);
        }
    }

    @Override
    public Optional<Mark> findById(int markId) {
        String sql = "SELECT mark_id, student_id, course_id, score FROM marks WHERE mark_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, markId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find mark", e);
        }
    }

    @Override
    public List<Mark> findByStudentId(int studentId) {
        String sql = "SELECT mark_id, student_id, course_id, score FROM marks WHERE student_id = ? ORDER BY course_id";
        List<Mark> results = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find marks by student", e);
        }
        return results;
    }

    @Override
    public List<Mark> findByCourseId(int courseId) {
        String sql = "SELECT mark_id, student_id, course_id, score FROM marks WHERE course_id = ? ORDER BY student_id";
        List<Mark> results = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find marks by course", e);
        }
        return results;
    }

    @Override
    public boolean update(Mark mark) {
        String sql = "UPDATE marks SET student_id = ?, course_id = ?, score = ? WHERE mark_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mark.getStudentId());
            ps.setInt(2, mark.getCourseId());
            ps.setDouble(3, mark.getScore());
            ps.setInt(4, mark.getMarkId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update mark", e);
        }
    }

    @Override
    public boolean delete(int markId) {
        String sql = "DELETE FROM marks WHERE mark_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, markId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete mark", e);
        }
    }

    private Mark mapRow(ResultSet rs) throws SQLException {
        return new Mark(
                rs.getInt("mark_id"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getDouble("score")
        );
    }
}