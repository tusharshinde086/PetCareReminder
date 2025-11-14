package com.petcare.servlet;

import com.petcare.dao.ActivityDAO;
import com.petcare.model.Activity;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddActivityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("activity_name");
        String time = request.getParameter("activity_time");

        Activity a = new Activity();
        a.setActivityName(name);
        a.setActivityTime(time);
        a.setStatus("Pending");

        ActivityDAO.addActivity(a);

        response.sendRedirect("DashboardServlet");
    }
}
