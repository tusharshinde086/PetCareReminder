package com.petcare.servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddActivityServlet")
public class AddActivityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petcare";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String activityName = request.getParameter("activityName");
        String description = request.getParameter("description");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Insert new activity into table
            String sql = "INSERT INTO activities (activity_name, description) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, activityName);
            ps.setString(2, description);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                request.setAttribute("message", "Activity added successfully!");
            } else {
                request.setAttribute("message", "Failed to add activity. Try again!");
            }

            RequestDispatcher rd = request.getRequestDispatcher("viewactivities.jsp");
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
