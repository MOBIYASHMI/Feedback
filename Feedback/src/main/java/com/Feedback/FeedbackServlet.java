package com.Feedback;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackServlet extends HttpServlet {

    private static final String DB_URL="jdbc:mysql://localhost:3306/feedback_db";
    private static final String DB_USER="root";
    private static final String DB_PASSWORD="PoojaYashu@2001";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name=request.getParameter("name");
        String bookName=request.getParameter("bookname");
        String feedBack=request.getParameter("feedback");

        try(Connection connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)) {
            String query="INSERT INTO feedback (name, bookname, feedback) VALUES (?, ?, ?)";
            try(PreparedStatement statement=connection.prepareStatement(query)) {
                statement.setString(1,name);
                statement.setString(2,bookName);
                statement.setString(3,feedBack);

                statement.executeUpdate();
            }
            response.getWriter().println("Feedback submitted successfully");
        } catch (SQLException e) {
            response.getWriter().println("Error submitting feedback");
        }
    }

}
