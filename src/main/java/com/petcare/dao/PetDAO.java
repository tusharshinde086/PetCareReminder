package com.petcare.dao;

import com.petcare.model.Pet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    // Add a new pet
    public static boolean addPet(Pet pet) {
        String sql = "INSERT INTO pets (pet_name, type, breed, age, gender) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getType());
            ps.setString(3, pet.getBreed());
            ps.setInt(4, pet.getAge());
            ps.setString(5, pet.getGender());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding pet: " + e.getMessage());
            return false;
        }
    }

    // Retrieve all pets
    public static List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM pets ORDER BY pet_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pet pet = mapResultSetToPet(rs);
                petList.add(pet);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching pets: " + e.getMessage());
        }

        return petList;
    }

    // Fetch pet by ID
    public static Pet getPetById(int id) {
        Pet pet = null;
        String sql = "SELECT * FROM pets WHERE pet_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pet = mapResultSetToPet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching pet by ID: " + e.getMessage());
        }

        return pet;
    }

    // Update pet details
    public static boolean updatePet(Pet pet) {
        String sql = "UPDATE pets SET pet_name=?, type=?, breed=?, age=?, gender=? WHERE pet_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getType());
            ps.setString(3, pet.getBreed());
            ps.setInt(4, pet.getAge());
            ps.setString(5, pet.getGender());
            ps.setInt(6, pet.getPetId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating pet: " + e.getMessage());
            return false;
        }
    }

    // Delete pet by ID
    public static boolean deletePet(int id) {
        String sql = "DELETE FROM pets WHERE pet_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting pet: " + e.getMessage());
            return false;
        }
    }

    // Helper method to map ResultSet to Pet object
    private static Pet mapResultSetToPet(ResultSet rs) throws SQLException {
        Pet pet = new Pet();
        pet.setPetId(rs.getInt("pet_id"));
        pet.setPetName(rs.getString("pet_name"));
        pet.setType(rs.getString("type"));
        pet.setBreed(rs.getString("breed"));
        pet.setAge(rs.getInt("age"));
        pet.setGender(rs.getString("gender"));
        return pet;
    }
}
