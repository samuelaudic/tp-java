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
     *
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
     *
     * @param inputFile the input file to process
     */
    private static void processFile(File inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            File outputFile = new File(inputFile.getParent(), inputFile.getName().replace(".op", ".res"));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] parts = line.split(" ");
                        if (parts.length != 3) {
                            throw new LineDecodingException("Incorrect syntax on line: " + line);
                        }

                        double operand1 = Double.parseDouble(parts[0].trim());
                        double operand2 = Double.parseDouble(parts[1].trim());
                        String operator = parts[2].trim();

                        Operation operation = OperationFactory.createOperation(operator);
                        double result = operation.perform(operand1, operand2);

                        writer.write(Double.toString(result));
                        writer.newLine();
                    } catch (NumberFormatException | LineDecodingException e) {
                        // If an operand is not a valid number
                        writer.write("ERROR: Invalid operand on line : " + line);
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                // Handle I/O errors when writing to the output file
                System.out.println("Error writing to the output file: " + outputFile.getName());
            }
        } catch (IOException e) {
            // Handle I/O errors when reading the input file
            System.out.println("Error reading the input file: " + inputFile.getName());
        }
    }



}
