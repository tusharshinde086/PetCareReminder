package com.petcare.dao;

import com.petcare.model.Activity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {

    public static boolean addActivity(Activity activity) {
        String sql = "INSERT INTO activities (activity_name, activity_time, status) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, activity.getActivityName());
            ps.setString(2, activity.getActivityTime());
            ps.setString(3, activity.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding activity: " + e.getMessage());
            return false;
        }
    }

    public static List<Activity> getAllActivities() {
        List<Activity> list = new ArrayList<>();
        String sql = "SELECT * FROM activities ORDER BY activity_time ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Activity a = new Activity();
                a.setActivityId(rs.getInt("id"));
                a.setActivityName(rs.getString("activity_name"));
                a.setActivityTime(rs.getString("activity_time"));
                a.setStatus(rs.getString("status"));
                list.add(a);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching activities: " + e.getMessage());
        }

        return list;
    }

    public static boolean updateStatus(int id, String status) {
        String sql = "UPDATE activities SET status=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating status: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteActivity(int id) {
        String sql = "DELETE FROM activities WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting activity: " + e.getMessage());
            return false;
        }
    }
}
