package com.example.sqlsecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection connection = null;
    private ConnectionProvider() {
    }
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                connection = DriverManager.getConnection(
                        "jdbc:oracle:thin:@195.182.202.156:15215/XEPDB1",
                        "student_in91_07",
                        "student");
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
