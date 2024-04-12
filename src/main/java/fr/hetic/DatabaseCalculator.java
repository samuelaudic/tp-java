package fr.hetic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseCalculator {

    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.connect()) {
            DatabaseProcessor.process(conn);
            System.out.println("process done");
        } catch (SQLException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
