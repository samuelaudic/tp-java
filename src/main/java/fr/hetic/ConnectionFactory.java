package fr.hetic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The ConnectionFactory class provides a method to establish a connection to the PostgreSQL database.
 */
public class ConnectionFactory {

    private static final String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
    private static final String user = "etudiant";
    private static final String password = "MT4@hetic2324";

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @return a Connection object representing the connection to the database
     * @throws SQLException if a database access error occurs or the URL is null
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

