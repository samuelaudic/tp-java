package fr.hetic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DatabaseConnector {

    private static final String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
    private static final String user = "etudiant";
    private static final String password = "MT4@hetic2324";

    public static void main(String[] args) {
        try (Connection conn = connect()) {
            process(conn);
        } catch (SQLException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private static void process(Connection conn) throws SQLException, IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.res"))) {
            String sql = "SELECT F.NOM, L.PARAM1, L.PARAM2, L.OPERATEUR FROM LIGNE L JOIN FICHIER F ON L.FICHIER_ID = F.ID WHERE F.TYPE = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "OP");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int param1 = rs.getInt("PARAM1");
                        int param2 = rs.getInt("PARAM2");
                        String operator = rs.getString("OPERATEUR");
                        String name = rs.getString("NOM");

                        Operation operation = OperationFactory.createOperation(operator);
                        double result = operation.perform(param1, param2);

                        // Write result to file
                        writer.write("NOM : " + name + ", RESULTAT: " + result);
                        writer.newLine();
                    }
                }
            }
        }
    }
}
