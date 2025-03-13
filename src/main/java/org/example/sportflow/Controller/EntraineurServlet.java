package org.example.sportflow.Controller;

import org.example.sportflow.DAO.EntraineurDAO;
import org.example.sportflow.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/coachDashboard")
public class EntraineurServlet extends HttpServlet {
    private EntraineurDAO entraineurDAO;

    @Override
    public void init() throws ServletException {
        try {
            entraineurDAO = new EntraineurDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize EntraineurDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null || currentUser.getRole() != User.Role.entraineur) {
            response.sendRedirect("login");
            return;
        }

        try {
            request.setAttribute("entraineur", entraineurDAO.getEntraineurById(currentUser.getId(), currentUser));
            request.setAttribute("seances", entraineurDAO.afficherSeances(currentUser));
            request.getRequestDispatcher("WEB-INF/dashboards/coachDashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving coach data", e);
        }
    }
}