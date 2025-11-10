package com.petcare.servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EditPetServlet")
public class EditPetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petcare";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from form
        int petId = Integer.parseInt(request.getParameter("petId"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        int age = Integer.parseInt(request.getParameter("age"));
        String owner = request.getParameter("owner");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Load driver and connect
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Update query
            String sql = "UPDATE pets SET name=?, type=?, age=?, owner=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setInt(3, age);
            ps.setString(4, owner);
            ps.setInt(5, petId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                request.setAttribute("message", "Pet details updated successfully!");
            } else {
                request.setAttribute("message", "Pet not found or update failed!");
            }

            // Forward to JSP page
            RequestDispatcher rd = request.getRequestDispatcher("viewpets.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);

        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
