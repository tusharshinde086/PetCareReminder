package com.petcare.dao;

import com.petcare.model.PetReminder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReminderDAO {

    // Add new reminder
    public static boolean addReminder(PetReminder reminder) {
        String sql = "INSERT INTO reminders (pet_id, activity, time, status) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reminder.getPetId()); // Use pet_id instead of pet_name
            ps.setString(2, reminder.getActivity());
            ps.setString(3, reminder.getTime());
            ps.setString(4, reminder.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding reminder: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Fetch all reminders (for dashboard)
    public static List<PetReminder> getAllReminders() {
        List<PetReminder> list = new ArrayList<>();
        String sql = "SELECT r.id, r.pet_id, p.pet_name, r.activity, r.time, r.status " +
                     "FROM reminders r JOIN pets p ON r.pet_id = p.pet_id " +
                     "ORDER BY r.time ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PetReminder r = new PetReminder();
                r.setId(rs.getInt("id"));
                r.setPetId(rs.getInt("pet_id"));
                r.setPetName(rs.getString("pet_name"));
                r.setActivity(rs.getString("activity"));
                r.setTime(rs.getString("time"));
                r.setStatus(rs.getString("status"));
                list.add(r);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching reminders: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    // Update reminder status (Done / Pending / Overdue)
    public static boolean updateStatus(int id, String status) {
        String sql = "UPDATE reminders SET status = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating reminder status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Delete a reminder by ID
    public static boolean deleteReminder(int id) {
        String sql = "DELETE FROM reminders WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting reminder: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Fetch reminders by pet_id
    public static List<PetReminder> getRemindersByPetId(int petId) {
        List<PetReminder> list = new ArrayList<>();
        String sql = "SELECT id, pet_id, activity, time, status FROM reminders WHERE pet_id = ? ORDER BY time ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, petId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PetReminder r = new PetReminder();
                    r.setId(rs.getInt("id"));
                    r.setPetId(rs.getInt("pet_id"));
                    r.setActivity(rs.getString("activity"));
                    r.setTime(rs.getString("time"));
                    r.setStatus(rs.getString("status"));
                    list.add(r);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching reminders by pet ID: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }
}
