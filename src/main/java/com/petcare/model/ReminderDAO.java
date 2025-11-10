package com.petreminder.dao;

import java.sql.*;
import java.util.*;
import com.petreminder.model.PetReminder;

public class ReminderDAO {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/petreminder_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // Establish connection to database
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found!", e);
        }
    }

    // Add new reminder
    public static boolean addReminder(PetReminder reminder) {
        String sql = "INSERT INTO reminders (pet_name, activity, time, status) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reminder.getPetName());
            ps.setString(2, reminder.getActivity());
            ps.setString(3, reminder.getTime());
            ps.setString(4, reminder.getStatus());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error adding reminder: " + e.getMessage());
            return false;
        }
    }

    // Fetch all reminders
    public static List<PetReminder> getAllReminders() {
        List<PetReminder> reminders = new ArrayList<>();
        String sql = "SELECT * FROM reminders ORDER BY time ASC";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PetReminder reminder = new PetReminder();
                reminder.setId(rs.getInt("id"));
                reminder.setPetName(rs.getString("pet_name"));
                reminder.setActivity(rs.getString("activity"));
                reminder.setTime(rs.getString("time"));
                reminder.setStatus(rs.getString("status"));
                reminders.add(reminder);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching reminders: " + e.getMessage());
        }

        return reminders;
    }

    // Delete reminder by ID
    public static boolean deleteReminder(int id) {
        String sql = "DELETE FROM reminders WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting reminder: " + e.getMessage());
            return false;
        }
    }

    // Update reminder status (optional)
    public static boolean updateStatus(int id, String status) {
        String sql = "UPDATE reminders SET status = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating reminder status: " + e.getMessage());
            return false;
        }
    }
}
