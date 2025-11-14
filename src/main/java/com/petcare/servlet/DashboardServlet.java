package com.petcare.servlet;

import com.petcare.dao.ActivityDAO;
import com.petcare.model.Activity;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Activity> activities = ActivityDAO.getAllActivities();
        request.setAttribute("activities", activities);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
