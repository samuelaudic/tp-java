package fr.hetic;

import java.io.IOException;
import java.sql.*;

public class DatabaseCalculator {

    private static final String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
    private static final String user = "etudiant";
    private static final String password = "MT4@hetic2324";

    public static void main(String[] args) {
        try (Connection conn = connect()) {
            DatabaseProcessor.process(conn);
        } catch (SQLException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
