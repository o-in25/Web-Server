package edu.csci340.web.util;


import java.io.*;

public class FileDumper {
    public static void outToFile(String fileName, String data) throws UnsupportedEncodingException, FileNotFoundException {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            File dir = new File("./");
            dir.mkdirs();
            File tmp = new File(dir, fileName);
            tmp.createNewFile();
            fileWriter = new FileWriter(tmp);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.newLine(); //this is not actually needed for html files - can make your code more readable though
            bufferedWriter.close(); //make sure you close the writer object
        } catch (Exception e) {
            //catch any exceptions here
        }
    }
}
