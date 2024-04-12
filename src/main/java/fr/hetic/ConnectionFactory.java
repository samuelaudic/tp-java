package fr.hetic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
    private static final String user = "etudiant";
    private static final String password = "MT4@hetic2324";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

