package org.example.sportflow.Controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sportflow.DAO.EntraineurDAO;
import org.example.sportflow.Model.Entraineur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/entraineur")
public class EntraineurServlet extends HttpServlet {
    private EntraineurDAO entraineurDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            entraineurDAO = new EntraineurDAO();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize EntraineurDAO", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "afficher";
        }
        try {
            switch (action.toLowerCase()) {
                case "ajouter":
                    ajouterEnt(req, resp);
                    break;
                case "modifier":
                    modifierEnt(req, resp);
                    break;
                case "supprimer":
                    supprimerEnt(req, resp);
                    break;
                case "afficher":
                default:
                    afficher(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("ajouter".equalsIgnoreCase(action)) {
                saveNewEntraineur(req, resp);
            } else if ("modifier".equalsIgnoreCase(action)) {
                updateEntraineur(req, resp);
            } else {
                afficher(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error during POST", e);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid ID format");
            try {
                afficher(req, resp);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void afficher(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Entraineur> entraineurs = entraineurDAO.AfficherEntraineur();
        req.setAttribute("entraineurs", entraineurs);
        req.getRequestDispatcher("/WEB-INF/entraineur/entraineur-list.jsp").forward(req, resp);
    }

    private void supprimerEnt(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            entraineurDAO.supprimerEntraineur(id);
        }
        resp.sendRedirect(req.getContextPath() + "/entraineur");
    }

    private void modifierEnt(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            Entraineur entraineur = entraineurDAO.getEntraineurById(id);
            if (entraineur != null) {
                req.setAttribute("entraineur", entraineur);
                req.getRequestDispatcher("/WEB-INF/entraineur/entraineur-edit.jsp").forward(req, resp);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/entraineur");
    }

    private void ajouterEnt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/entraineur/entraineur-add.jsp").forward(req, resp);
    }

    private void saveNewEntraineur(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String idParam = req.getParameter("id");
        String nom = req.getParameter("nom");
        String specialite = req.getParameter("specialite");

        if (idParam != null && nom != null && specialite != null) {
            Entraineur entraineur = new Entraineur();
            entraineur.setId(Integer.parseInt(idParam));
            entraineur.setNom(nom);
            entraineur.setSpecialite(specialite);
            entraineurDAO.ajouterEntraineur(entraineur);
        }
        resp.sendRedirect(req.getContextPath() + "/entraineur");
    }

    private void updateEntraineur(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String idParam = req.getParameter("id");
        String nom = req.getParameter("nom");
        String specialite = req.getParameter("specialite");

        if (idParam != null && nom != null && specialite != null) {
            Entraineur entraineur = new Entraineur();
            entraineur.setId(Integer.parseInt(idParam));
            entraineur.setNom(nom);
            entraineur.setSpecialite(specialite);
            entraineurDAO.modifierEntraineur(entraineur);
        }
        resp.sendRedirect(req.getContextPath() + "/entraineur");
    }
}