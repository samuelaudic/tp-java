package fr.hetic;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseCalculator {

    public static void main(String[] args) {
        try {
            Properties properties = loadProperties();
            String dataReader = properties.getProperty("data.reader");

            if ("JDBC".equals(dataReader)) {
                // Utiliser le lecteur JDBC
                try (Connection conn = ConnectionFactory.connect()) {
                    DatabaseProcessor.process(conn);
                    System.out.println("process done");
                }
            } else {
                System.err.println("Invalid data reader specified in application.properties");
            }
        } catch (IOException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseCalculator.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        }
        return properties;
    }
}
