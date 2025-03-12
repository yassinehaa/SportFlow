package org.example.sportflow.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ("/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/Auth/Login.jsp").forward(req,res);

    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    }

}
