package com.example.loginpage.Services;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


@SuppressWarnings("ALL")
public class FileService {
    public static String[][] readAllCSV(String filename) throws FileNotFoundException {
        File CSVFile = new File("src/main/resources/data/" + filename + ".csv");
        Scanner read = new Scanner(CSVFile);

        ArrayList<String[]> lines = new ArrayList<>();
        while (read.hasNextLine()) {
            lines.add(read.nextLine().split(","));
        }

        read.close();
        return lines.toArray(new String[lines.size()][]);
    }
    public static String[] readLineCSV(String filename) throws FileNotFoundException {
        File CSVFile = new File("src/main/resources/data/" + filename + ".csv");
        Scanner read = new Scanner(CSVFile);

        String[] line = read.nextLine().split(",");
        read.close();
        return line;
    }

    public static void deleteRecordCSV(String id) throws IOException {
        File InputFile = getFile(id);
        File TempFile = new File("src/main/resources/data/temp.csv");
        Scanner read = new Scanner(InputFile);
        FileWriter writer = new FileWriter(TempFile);

        do {
            String line = read.nextLine();
            if (!line.contains(id)) {
                writer.write(line + "\n");
            }
        } while (read.hasNextLine());
        writer.close();
        read.close();
        InputFile.delete();
        TempFile.renameTo(InputFile);
    }

    public static void insertRecordCSV(String id, String[] data) {
        File CSVFile = getFile(id);


    }

    private static File getFile(String id) {
        File CSVFile;
        boolean isUser = id.startsWith("B");

        if (isUser) {
            CSVFile = new File("src/main/resources/data/users.csv");
        } else {
            CSVFile = new File("src/main/resources/data/courses.csv");
        }

        return CSVFile;
    }

}
/*
if (ID starts w/ B) {
    File = user.csv
} else { course.csv }
 */