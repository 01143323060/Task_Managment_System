package DataBase;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManager {

    private static final String BASE_PATH = "Resources" + File.separator;

    static {
        try {
            Files.createDirectories(Paths.get(BASE_PATH));
        } catch (IOException e) {
            System.err.println("Could not create Resources folder: " + e.getMessage());
        }
    }

    public static void writeFile(String fileName, List<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                pw.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
        }
    }

    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return lines;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
        }
        return lines;
    }
}