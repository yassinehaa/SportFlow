package org.example.sportflow.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sportflow.DAO.AdminDAO;
import org.example.sportflow.Model.Entraineur;
import org.example.sportflow.Model.Membre;
import org.example.sportflow.Model.Seance;
import org.example.sportflow.Model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
            request.getRequestDispatcher("WEB-INF/dashboards/adminDashboard.jsp").forward(request, response);
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
                    User user = new User(
                            request.getParameter("username"),
                            request.getParameter("password"),
                            User.Role.valueOf(request.getParameter("role"))
                    );
                    adminDAO.ajouterUser(user, currentUser);
                    break;
                case "addMembre":
                    Membre membre = new Membre(
                            request.getParameter("username"),
                            request.getParameter("password"),
                            request.getParameter("name")
                    );
                    adminDAO.ajouterMembre(membre, currentUser);
                    break;
                case "addEntraineur":
                    Entraineur entraineur = new Entraineur(
                            request.getParameter("username"),
                            request.getParameter("password"),
                            request.getParameter("name"),
                            request.getParameter("specialite")
                    );
                    adminDAO.ajouterEntraineur(entraineur, currentUser);
                    break;
                case "addSeance":
                    Seance seance = new Seance(
                            Integer.parseInt(request.getParameter("idmembre")),
                            Integer.parseInt(request.getParameter("identraineur")),
                            LocalDateTime.parse(request.getParameter("dateetheure"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    );
                    adminDAO.ajouterSeance(seance, currentUser);
                    break;

                case "updateUser":
                    User updatedUser = new User(
                            request.getParameter("username"),
                            request.getParameter("password"),
                            User.Role.valueOf(request.getParameter("role"))
                    );
                    updatedUser.setId(Integer.parseInt(request.getParameter("id")));
                    adminDAO.modifierUser(updatedUser, currentUser);
                    break;
                case "updateMembre":
                    Membre updatedMembre = new Membre(
                            request.getParameter("username"),
                            request.getParameter("password"),
                            request.getParameter("name")
                    );
                    updatedMembre.setId(Integer.parseInt(request.getParameter("id")));
                    adminDAO.modifierMembre(updatedMembre, currentUser);
                    break;
                case "updateEntraineur":
                    Entraineur updatedEntraineur = new Entraineur(
                            request.getParameter("username"),
                            request.getParameter("password"),
                            request.getParameter("name"),
                            request.getParameter("specialite")
                    );
                    updatedEntraineur.setId(Integer.parseInt(request.getParameter("id")));
                    adminDAO.modifierEntraineur(updatedEntraineur, currentUser);
                    break;
                case "updateSeance":
                    Seance updatedSeance = new Seance(
                            Integer.parseInt(request.getParameter("idmembre")),
                            Integer.parseInt(request.getParameter("identraineur")),
                            LocalDateTime.parse(request.getParameter("dateetheure"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    );
                    updatedSeance.setId(Integer.parseInt(request.getParameter("id")));
                    adminDAO.modifierSeance(updatedSeance, currentUser);
                    break;

                // Delete
                case "deleteUser":
                    adminDAO.supprimerUser(Integer.parseInt(request.getParameter("id")), currentUser);
                    break;
                case "deleteMembre":
                    adminDAO.supprimerMembre(Integer.parseInt(request.getParameter("id")), currentUser);
                    break;
                case "deleteEntraineur":
                    adminDAO.supprimerEntraineur(Integer.parseInt(request.getParameter("id")), currentUser);
                    break;
                case "deleteSeance":
                    adminDAO.supprimerSeance(Integer.parseInt(request.getParameter("id")), currentUser);
                    break;

                default:
                    request.setAttribute("error", "Unknown action: " + action);
                    doGet(request, response);
                    return;
            }
            response.sendRedirect("adminDashboard");
        } catch (SQLException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }
}