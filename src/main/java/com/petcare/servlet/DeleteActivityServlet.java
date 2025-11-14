package com.petcare.servlet;

import com.petcare.dao.ActivityDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteActivityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        ActivityDAO.deleteActivity(id);

        response.sendRedirect("DashboardServlet");
    }
}
