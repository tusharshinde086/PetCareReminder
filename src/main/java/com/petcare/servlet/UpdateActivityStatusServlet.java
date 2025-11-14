package com.petcare.servlet;

import com.petcare.dao.ActivityDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateActivityStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        ActivityDAO.updateStatus(id, status);

        response.sendRedirect("DashboardServlet");
    }
}
