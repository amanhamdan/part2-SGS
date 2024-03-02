package com.example.servletjdbcproject.dao;

import com.example.servletjdbcproject.model.Grade;
import com.example.servletjdbcproject.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/users?useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
    public List<Grade> getGradesByUserId(int userId) {
        List<Grade> grades = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT subject, grade FROM grades WHERE student_id = ?")) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String subject = resultSet.getString("subject");
                String grade = resultSet.getString("grade");
                grades.add(new Grade(subject, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database exception
        }

        return grades;
    }
    public int getUserIdByUsername(String username) {
        int userId = 0;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM user WHERE username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database exception
        }

        return userId;
    }

    //method to update grade
    public boolean updateGrade(String studentId, String subject, String newGrade) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE grades SET grade = ? WHERE student_id = ? AND subject = ?")) {
            preparedStatement.setString(1, newGrade);
            preparedStatement.setString(2, studentId);
            preparedStatement.setString(3, subject);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean isValidUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; //
        }
    }

    public String getUserRole(String username) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT role FROM users WHERE username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role"); // Returns the role associated with the given username
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if the username does not exist in the database or in case of any exception
    }

    public int registerEmployee(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO user" +
                "  ( first_name, last_name, username, password, role) VALUES " +
                " ( ?, ?, ?, ?,?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "root");

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());


            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {

            printSQLException(e);
        }
        return result;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
