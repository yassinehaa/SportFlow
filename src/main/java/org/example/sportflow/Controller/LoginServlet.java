package org.example.sportflow.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.sportflow.DAO.UserDAO;
import org.example.sportflow.Model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        try {
            userDAO = new UserDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Auth/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && password != null && password.equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);
                switch (user.getRole()) {
                    case admin:
                        response.sendRedirect("/adminDashboard");
                        break;
                    case membre:
                        response.sendRedirect("/memberDashboard");
                        break;
                    case entraineur:
                        response.sendRedirect("/coachDashboard");
                        break;
                    default:
                        request.setAttribute("error", "Unknown role");
                        request.getRequestDispatcher("/Auth/Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/Auth/Login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error during login", e);
        }
    }
}