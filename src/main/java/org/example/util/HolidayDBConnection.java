package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HolidayDBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/holiday";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234567890";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
