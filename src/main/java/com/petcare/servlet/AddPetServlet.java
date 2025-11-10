package com.petcare.servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddPetServlet")
public class AddPetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection settings
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petcare";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get data from form fields
        String petName = request.getParameter("petName");
        String petType = request.getParameter("petType");
        String customType = request.getParameter("customType");
        String activity = request.getParameter("activity");
        String customActivity = request.getParameter("customActivity");
        String date = request.getParameter("date");

        // If user selected "Other", use custom inputs
        if ("Other".equalsIgnoreCase(petType) && customType != null && !customType.trim().isEmpty()) {
            petType = customType;
        }
        if ("Other".equalsIgnoreCase(activity) && customActivity != null && !customActivity.trim().isEmpty()) {
            activity = customActivity;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // SQL insert query
            String sql = "INSERT INTO pets (name, type, activity, reminder_date) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, petName);
            ps.setString(2, petType);
            ps.setString(3, activity);
            ps.setString(4, date);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                request.setAttribute("message", "Pet added successfully!");
            } else {
                request.setAttribute("message", "Failed to add pet. Try again!");
            }

            // Forward to success or view page
            RequestDispatcher rd = request.getRequestDispatcher("viewpets.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
