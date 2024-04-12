package fr.hetic;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class CalculatorMain {

    /**
     * Main method to start the application.
     * Reads configuration from the application.properties file and performs calculations accordingly.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            Properties properties = loadProperties();
            String dataReader = properties.getProperty("data.reader");
            String[] filePath = {properties.getProperty("filePath")};

            if ("FILE".equals(dataReader)) {
                // Utiliser le lecteur de fichier
                FileProcessor.main(filePath);
            } else if ("JDBC".equals(dataReader)) {
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

    /**
     * Loads properties from the application.properties file.
     *
     * @return Properties object containing the loaded properties
     * @throws IOException if an error occurs while reading the file
     */
    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = CalculatorMain.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        }
        return properties;
    }
}
