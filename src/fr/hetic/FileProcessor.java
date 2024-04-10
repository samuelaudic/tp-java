package fr.hetic;

import java.io.*;

public class FileProcessor {

    // Exception class for line decoding errors
    static class LineDecodingException extends Exception {
        public LineDecodingException(String message) {
            super(message);
        }
    }

    /**
     * Main method to process files in a given directory.
     * @param args an array of strings containing the path to the directory containing the files to be processed
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileProcessor <folder_path>");
            return;
        }

        File folder = new File(args[0]);
        System.out.println(folder);

        if (!folder.isDirectory()) {
            System.out.println("The folder path is not a directory");
            return;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".op")) {
                    processFile(file);
                }
            }
        } else {
            System.out.println("Erreur lors de la lecture du répertoire.");
            throw new AssertionError();
        }

    }

    /**
     * Processes an input file, performs specified operations, and generates an output file with the results.
     * @param inputFile the input file to process
     */
    private static void processFile(File inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            File outputFile = new File(inputFile.getParent(), inputFile.getName().replace(".op", ".res"));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        double result = getResult(line);
                        writer.write(Double.toString(result));
                        writer.newLine();
                    } catch (NumberFormatException | LineDecodingException e) {
                        // Si une opérande n'est pas un nombre valide
                        writer.write("ERROR: Opérande invalide sur la ligne : " + line);
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                // Gérer les erreurs d'E/S lors de l'écriture dans le fichier de sortie
                System.out.println("Erreur lors de l'écriture du fichier de sortie: " + outputFile.getName());
            }
        } catch (IOException e) {
            // Gérer les erreurs d'E/S lors de la lecture du fichier d'entrée
            System.out.println("Erreur lors de la lecture du fichier d'entrée: " + inputFile.getName());
        }
    }

    /**
     * Extracts numeric values and operator from a given line, then performs the corresponding operation.
     * @param line the line to process, in the format "numeric numeric operator"
     * @throws LineDecodingException if the line is not decodable according to the expected syntax
     */
    private static double getResult(String line) throws LineDecodingException {
        String[] parts = line.split(" ");
        if (parts.length != 3) {
            throw new LineDecodingException("Syntaxe incorrecte sur la ligne: " + line);
        }

        double operand1 = Double.parseDouble(parts[0].trim());
        double operand2 = Double.parseDouble(parts[1].trim());
        String operator = parts[2].trim();

        return performOperation(operand1, operand2, operator);
    }

    /**
     * Performs an arithmetic operation between two operands using the specified operator.
     * @param operand1 the first operand
     * @param operand2 the second operand
     * @param operator the operator of the operation (among "+", "-", "*")
     * @throws IllegalArgumentException if the operator is not supported
     */
    private static double performOperation(double operand1, double operand2, String operator) {
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            default -> throw new IllegalArgumentException("Opérateur non supporté : " + operator);
        };
    }

}
