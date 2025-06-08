package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL  =
        "jdbc:mysql://localhost:3306/accounts_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "your_password_here";

    private static Connection conn;

    private DatabaseConnection() { }

    /**
     * Повертає єдине з’єднання з базою.
     * Якщо з’єднання не існує або закрите — створює нове.
     */
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        return conn;
    }
}