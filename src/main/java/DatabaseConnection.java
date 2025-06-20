//package org.example.JDBCUtil;

import org.example.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static {
        try {
            // Cargar el driver de MySQL
            Class.forName(ConfigManager.getDbDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se pudo cargar el driver de MySQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                ConfigManager.getDbUrl(),
                ConfigManager.getDbUsername(),
                ConfigManager.getDbPassword()
        );
    }
}