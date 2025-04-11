package com.example.loginpage.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileService {
    public static String[][] readAllCSV(String filename) throws FileNotFoundException {
        File CSVFile = new File("src/main/resources/data/" + filename + ".csv");
        Scanner read = new Scanner(CSVFile);

        ArrayList<String[]> lines = new ArrayList<>();
        for (String line = read.nextLine(); read.hasNextLine(); line = read.nextLine()) {
            lines.add(line.split(","));
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

    public static void deleteRecordCSV(int id) {
    }

    public static void insertRecordCSV(int id, String[] data) {
    }

}
/*
if (ID starts w/ B) {
    File = user.csv
} else { course.csv }
 */