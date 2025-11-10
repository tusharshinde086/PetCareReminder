package com.petcare.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import com.petcare.dao.PetDAO;
import com.petcare.model.Pet;
import com.petcare.model.Activity;

public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PetDAO dao = new PetDAO();
        List<Pet> pets = dao.getAllPets();
        List<Activity> acts = dao.getActivities();

        req.setAttribute("pets", pets);
        req.setAttribute("activities", acts);
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}
