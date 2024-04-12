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
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT F.NOM, L.PARAM1, L.PARAM2, L.OPERATEUR, L.POSITION FROM LIGNE L JOIN FICHIER F ON L.FICHIER_ID = F.ID WHERE F.TYPE = 'OP'";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql);
                 BufferedWriter writer = new BufferedWriter(new FileWriter("output.res"))) {

                while (rs.next()) {
                    int param1 = rs.getInt("PARAM1");
                    int param2 = rs.getInt("PARAM2");
                    String operator = rs.getString("OPERATEUR");
                    //int index = rs.getInt("POSITION");
                    String name = rs.getString("NOM");

                    Operation operation = OperationFactory.createOperation(operator);
                    double result = operation.perform(param1, param2);

                    // Write result to file
                    writer.write("NOM : " + name + ", RESULTAT: " + result);
                    writer.newLine();
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error: SQLException. " + e);
        }
    }
}
