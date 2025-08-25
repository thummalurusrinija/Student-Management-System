package com.example.sms.dao.jdbc;

import com.example.sms.config.ConnectionManager;
import com.example.sms.dao.AttendanceDao;
import com.example.sms.model.Attendance;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceJdbcDao implements AttendanceDao {
    @Override
    public int create(Attendance attendance) {
        String sql = "INSERT INTO attendance (attendance_id, student_id, date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, attendance.getAttendanceId());
            ps.setInt(2, attendance.getStudentId());
            ps.setDate(3, Date.valueOf(attendance.getDate()));
            ps.setString(4, attendance.getStatus().name());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert attendance", e);
        }
    }

    @Override
    public Optional<Attendance> findById(int attendanceId) {
        String sql = "SELECT attendance_id, student_id, date, status FROM attendance WHERE attendance_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, attendanceId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find attendance", e);
        }
    }

    @Override
    public List<Attendance> findByStudentId(int studentId) {
        String sql = "SELECT attendance_id, student_id, date, status FROM attendance WHERE student_id = ? ORDER BY date DESC";
        List<Attendance> results = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find attendance by student", e);
        }
        return results;
    }

    @Override
    public List<Attendance> findByDate(LocalDate date) {
        String sql = "SELECT attendance_id, student_id, date, status FROM attendance WHERE date = ? ORDER BY student_id";
        List<Attendance> results = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find attendance by date", e);
        }
        return results;
    }

    @Override
    public boolean update(Attendance attendance) {
        String sql = "UPDATE attendance SET student_id = ?, date = ?, status = ? WHERE attendance_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, attendance.getStudentId());
            ps.setDate(2, Date.valueOf(attendance.getDate()));
            ps.setString(3, attendance.getStatus().name());
            ps.setInt(4, attendance.getAttendanceId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update attendance", e);
        }
    }

    @Override
    public boolean delete(int attendanceId) {
        String sql = "DELETE FROM attendance WHERE attendance_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, attendanceId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete attendance", e);
        }
    }

    private Attendance mapRow(ResultSet rs) throws SQLException {
        Attendance a = new Attendance();
        a.setAttendanceId(rs.getInt("attendance_id"));
        a.setStudentId(rs.getInt("student_id"));
        a.setDate(rs.getDate("date").toLocalDate());
        a.setStatus(Attendance.Status.valueOf(rs.getString("status")));
        return a;
    }
}