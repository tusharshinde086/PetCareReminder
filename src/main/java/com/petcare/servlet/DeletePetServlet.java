package com.petcare.servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DeletePetServlet")
public class DeletePetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petcare";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int petId = Integer.parseInt(request.getParameter("petId"));
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Delete SQL statement
            String sql = "DELETE FROM pets WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, petId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                request.setAttribute("message", "Pet deleted successfully!");
            } else {
                request.setAttribute("message", "Pet not found or already deleted!");
            }

            // Redirect to JSP (list page)
            RequestDispatcher rd = request.getRequestDispatcher("viewpets.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);

        } finally {
            // Close connections
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
