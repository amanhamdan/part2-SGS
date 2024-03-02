package com.example.servletjdbcproject.controller;

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

        UserDao userDao = new UserDao();
        if (userDao.isValidUser(username, password)) {
            String role = userDao.getUserRole(username);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);

            if (role.equals("student")) {
                response.sendRedirect("viewGrades");
            } else if (role.equals("admin")) {
                response.sendRedirect("adminHome.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}