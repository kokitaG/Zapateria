package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (input == null) {
                throw new RuntimeException("No se pudo encontrar database.properties");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar database.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getDbUrl() {
        return getProperty("db.url");
    }

    public static String getDbUsername() {
        return getProperty("db.username");
    }

    public static String getDbPassword() {
        return getProperty("db.password");
    }

    public static String getDbDriver() {
        return getProperty("db.driver");
    }
}
