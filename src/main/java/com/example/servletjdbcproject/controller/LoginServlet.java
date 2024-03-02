package com.example.servletjdbcproject.controller;

// Import statements

import com.example.servletjdbcproject.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check credentials in database
        if (isValidUser(username, password)) {
            String role = getRole(username); // Assuming you have a method to retrieve user role from database
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);

            // Redirect based on role
            if (role.equals("student")) {
                response.sendRedirect("studentHome.jsp");
            } else if (role.equals("instructor")) {
                response.sendRedirect("instructorHome.jsp");
            } else if (role.equals("admin")) {
                response.sendRedirect("adminHome.jsp");
            }
        } else {
            response.sendRedirect("login.jsp"); // Redirect back to login page if credentials are invalid
        }
    }

    private boolean isValidUser(String username, String password) {
        UserDao userDao = new UserDao(); // Instantiate UserDao
        return userDao.isValidUser(username, password); // Assuming UserDao has isValidUser method
    }

    private String getRole(String username) {
        UserDao userDao = new UserDao(); // Instantiate UserDao
        return userDao.getUserRole(username); // Assuming UserDao has getUserRole method
    }
}
