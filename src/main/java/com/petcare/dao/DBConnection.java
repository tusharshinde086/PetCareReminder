package com.petcare.dao;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/petcare_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "tushar";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
