package org.example.sportflow.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sportflow.DAO.AdminDAO;
import org.example.sportflow.Model.Entraineur;
import org.example.sportflow.Model.Membre;
import org.example.sportflow.Model.User;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/adminDashboard")
public class AdminServlet extends HttpServlet {
    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        try {
            adminDAO = new AdminDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize AdminDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null || currentUser.getRole() != User.Role.admin) {
            response.sendRedirect("login");
            return;
        }

        try {
            request.setAttribute("users", adminDAO.afficherUsers(currentUser));
            request.setAttribute("membres", adminDAO.afficherMembres(currentUser));
            request.setAttribute("entraineurs", adminDAO.afficherEntraineurs(currentUser));
            request.setAttribute("seances", adminDAO.afficherSeances(currentUser));
            request.getRequestDispatcher("dashboards/adminDashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving data", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null || currentUser.getRole() != User.Role.admin) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        try {
            switch (action) {
                // Create
                case "addUser":
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String role = request.getParameter("role");

                    if (username == null || username.trim().isEmpty()) {
                        request.setAttribute("error", "Username cannot be empty");
                        doGet(request, response);
                        return;
                    }

                    User user = new User(username, password, User.Role.valueOf(role));
                    adminDAO.ajouterUser(user, currentUser);
                    break;

                case "addMembre":
                    String membreUsername = request.getParameter("username");
                    String membrePassword = request.getParameter("password");
                    String membreName = request.getParameter("name");

                    if (membreUsername == null || membreUsername.trim().isEmpty()) {
                        request.setAttribute("error", "Username cannot be empty");
                        doGet(request, response);
                        return;
                    }

                    Membre membre = new Membre(membreUsername, membrePassword, membreName);
                    membre.setRole(User.Role.membre); // Définir explicitement le rôle
                    adminDAO.ajouterMembre(membre, currentUser);
                    break;

                case "addEntraineur":
                    String entraineurUsername = request.getParameter("username");
                    String entraineurPassword = request.getParameter("password");
                    String entraineurName = request.getParameter("name");
                    String entraineurSpecialite = request.getParameter("specialite");

                    if (entraineurUsername == null || entraineurUsername.trim().isEmpty()) {
                        request.setAttribute("error", "Username cannot be empty");
                        doGet(request, response);
                        return;
                    }

                    Entraineur entraineur = new Entraineur(entraineurUsername, entraineurPassword, entraineurName, entraineurSpecialite);
                    entraineur.setRole(User.Role.entraineur); // Définir explicitement le rôle
                    adminDAO.ajouterEntraineur(entraineur, currentUser);
                    break;

                // Autres cas...
            }
            response.sendRedirect("adminDashboard");
        } catch (SQLException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }
}