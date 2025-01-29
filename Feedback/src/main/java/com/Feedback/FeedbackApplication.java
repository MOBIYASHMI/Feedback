package com.Feedback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.sql.*;

@SpringBootApplication
public class FeedbackApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackApplication.class, args);
	}

	private static final String DB_URL="jdbc:mysql://localhost:3306/feedback_db";
	private static final String DB_USER="root";
	private static final String DB_PASSWORD="PoojaYashu@2001";

	@Bean
	public ServletRegistrationBean<FeedbackServlet> feedbackServletServletRegistration(){
		ServletRegistrationBean<FeedbackServlet> registrationBean=
				new ServletRegistrationBean<>(new FeedbackServlet(),"/submitFeedback");

		return registrationBean;
	}

	@Override
	public void run(String... args){
		try(Connection connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)) {
			String query = "CREATE TABLE IF NOT EXISTS feedback (name VARCHAR(100) NOT NULL, bookname VARCHAR(100) NOT NULL, feedback TEXT NOT NULL)";
			Statement statement=connection.prepareStatement(query);
			statement.execute(query);
			System.out.println("Feedback table created or already exists");
		} catch (SQLException e) {
			e.printStackTrace();
        }
    }
}
