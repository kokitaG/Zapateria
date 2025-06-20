package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = JDBCUtil.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (in == null) {
                throw new RuntimeException("No se encuentra el archivo db.properties en el classpath.");
            }
            props.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error al cargar el archivo de propiedades: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.user");
        String password = props.getProperty("jdbc.password");

        if (url == null || user == null || password == null) {
            throw new SQLException("Faltan propiedades de conexión a la base de datos (url, user, o password).");
        }

        return DriverManager.getConnection(url, user, password);
    }
}
