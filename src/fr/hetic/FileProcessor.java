package fr.hetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileProcessor {
    public static void main(String[] args) throws AssertionError {

        if (args.length != 1) {
            System.out.println("Usage: java FileProcessor <folder_path>");
            return;
        }

        String folderPath = args[0];
        File folder = new File(args[0]);
        System.out.println(folder);

        if (!folder.isDirectory()) {
            System.out.println("Le chemin spécifié n'est pas un répertoire.");
            return;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                processFile(file);
            }
        } else {
            System.out.println("Erreur lors de la lecture du répertoire.");
            throw new AssertionError();
        }

    }

    private static void processFile(File inputFile) {


    }
}