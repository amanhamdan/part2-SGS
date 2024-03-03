package com.example.servletjdbcproject.controller;

import com.example.servletjdbcproject.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editGrade")
public class EditGradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String studentId = request.getParameter("studentId");
        String subject = request.getParameter("subject");
        String newGrade = request.getParameter("newGrade");

        UserDao userDao = new UserDao();
        boolean updated = userDao.updateGrade(studentId, subject, newGrade);

        if (updated) {
            //Grade updated successfully
            response.sendRedirect("adminHome.jsp");
        } else {
            //failed to update grade
            response.sendRedirect("error.jsp");
        }
    }
}