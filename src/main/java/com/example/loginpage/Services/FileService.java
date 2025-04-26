package com.example.loginpage.Services;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


@SuppressWarnings("ALL")
public class FileService {


    //Takes in the filename as a String then reads all of the data in that CSV, returns as a 2D String Array
    public static String[][] readAllCSV(String filename) throws FileNotFoundException {
        File CSVFile = new File("src/main/resources/data/" + filename + ".csv");
        Scanner read = new Scanner(CSVFile);

        //Stores every line as an ArrayList of 1D String Lists
        ArrayList<String[]> lines = new ArrayList<>();
        while (read.hasNextLine()) {
            lines.add(read.nextLine().split(","));
        }

        read.close();
        return lines.toArray(new String[lines.size()][]); //Converts the Array List to a 2D Array
    }

    //Reads the next line in a CSV file, returns a String[]
    public static String[] readLineCSV(String filename) throws FileNotFoundException {
        File CSVFile = new File("src/main/resources/data/" + filename + ".csv");
        Scanner read = new Scanner(CSVFile);

        String[] line = read.nextLine().split(",");
        read.close();
        return line;
    }

    //Deletes a record, doesn't require the filename because ID starts with "B", "C", or "P"
    //This gives me the file I need to edit by using the getFile() method.
    public static void deleteRecordCSV(String id) throws IOException {
        File InputFile = getFile(id);
        File TempFile = new File("src/main/resources/data/temp.csv");
        Scanner read = new Scanner(InputFile);
        FileWriter writer = new FileWriter(TempFile);

        //Rewrites the entire file except the record to be deleted to a temp file
        do {
            String line = read.nextLine();
            if (!line.contains(id)) {
                writer.write(line + "\n");
            }
        } while (read.hasNextLine());

        writer.close();
        read.close();
        InputFile.delete(); //Delete original CSV file
        TempFile.renameTo(InputFile); //Rename temp file to name of CSV file
    }

    //Inserts a record into a CSV file. Keeps the CSV Sorted mostly because of ease during troubleshooting
    public static void insertRecordCSV(String id, String[] data) throws IOException {
        File InputFile = getFile(id);
        File TempFile = new File("src/main/resources/data/temp.csv");
        Scanner read = new Scanner(InputFile);
        FileWriter writer = new FileWriter(TempFile);

        String writableData = "";
        for (int i = 0; i < data.length; i++) {
            writableData += (data[i] + ",");
        }
        writableData = writableData.substring(0, writableData.length() - 1);

        int idInt = Integer.parseInt(id.substring(1));

        idInt = idInt;
        String idBefore = String.valueOf(idInt - 1);

        //If the ID being inserted is 1001 Writes immediately to the first line (becuase 1001 should be the first ID)
        if (idInt == 1001){
            writer.write(writableData + "\n");
        }
        //Goes rewrites the whole file to a temp file
        //If the line being written to contains the id - 1
        //Then the line is written, followed by the record to be inserted
        //Otherwise just the line is written to the temp file
        do {
            String line = read.nextLine();
            if (line.contains(idBefore) && idInt != 1001) {
                writer.write(line + "\n");
                writer.write(writableData + "\n");
            } else {
                writer.write(line + "\n");
            }
        } while (read.hasNextLine());


        writer.close();
        read.close();
        InputFile.delete(); //Delete original CSV file
        TempFile.renameTo(InputFile); //Rename temp file to name of CSV file
    }

    //Calls deleteRecordCSV() and insertRecordCSV() in succession mostly to make updates a little more intuitive
    public static void updateRecordCSV(String id, String[] data) throws IOException {
        deleteRecordCSV(id);
        insertRecordCSV(id, data);
    }

    //Checks what Char the ID starts with to determine what CSV file the ID would be found in
    private static File getFile(String id) {
        File CSVFile;
        String idType = id.substring(0,1);

        switch (idType) {
            case "B":
                CSVFile = new File("src/main/resources/data/users.csv");
                break;
            case "P":
                CSVFile = new File("src/main/resources/data/professors.csv");
                break;
            case "C":
                CSVFile = new File("src/main/resources/data/courses.csv");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + idType);
        }
        return CSVFile;
    }
}
