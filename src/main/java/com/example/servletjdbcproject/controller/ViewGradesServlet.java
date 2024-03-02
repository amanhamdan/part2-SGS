package com.example.servletjdbcproject.controller;

import com.example.servletjdbcproject.dao.UserDao;
import com.example.servletjdbcproject.model.Grade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewGrades")
public class ViewGradesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        UserDao userDao = new UserDao();
        int userId = userDao.getUserIdByUsername(username);

        List<Grade> grades = userDao.getGradesByUserId(userId);

        request.setAttribute("grades", grades);
        request.getRequestDispatcher("/WEB-INF/studentGrades.jsp").forward(request, response);
    }
}