package org.example.sportflow.Controller;

import org.example.sportflow.DAO.MembreDAO;
import org.example.sportflow.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/memberDashboard")
public class MembreServlet extends HttpServlet {
    private MembreDAO membreDAO;

    @Override
    public void init() throws ServletException {

        try {
            membreDAO = new MembreDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null || currentUser.getRole() != User.Role.membre) {
            response.sendRedirect("login");
            return;
        }

        try {
            request.setAttribute("membre", membreDAO.getMembreById(currentUser.getId(), currentUser));
            request.setAttribute("seances", membreDAO.afficherSeances(currentUser));
            request.getRequestDispatcher("WEB-INF/dashboards/memberDashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving member data", e);
        }
    }
}