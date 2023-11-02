package org.example.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static final Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CRYPTO-DB",
                    "root",
                    ""
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error to connect into the database");
        }
    }

    private SingletonConnection() {
    }
    public static synchronized Connection getConnection() {
        return connection;
    }
}
