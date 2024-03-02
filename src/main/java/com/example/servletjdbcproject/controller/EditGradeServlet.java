package com.example.servletjdbcproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editGrade")
public class EditGradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //retrieve from data
        String studentId = request.getParameter("studentId");
        String subject = request.getParameter("subject");
        String newGrade = request.getParameter("newGrade");

        response.sendRedirect("adminHome.jsp");
    }
}