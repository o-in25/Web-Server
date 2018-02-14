package edu.xavier.cs.networking.serverexample.util;

import java.io.*;

public class FileRouter {

    public static void outToFile(String fileName, String encoding, String data) throws UnsupportedEncodingException, FileNotFoundException {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.newLine(); //this is not actually needed for html files - can make your code more readable though
            bufferedWriter.close(); //make sure you close the writer object
        } catch (Exception e) {
            //catch any exceptions here
        }
    }
}
