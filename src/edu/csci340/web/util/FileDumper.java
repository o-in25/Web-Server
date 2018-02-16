package edu.csci340.web.util;


import java.io.*;

public class FileDumper {

    // dumps data to a file with a given name
    public static void outToFile(String fileName, String data) throws UnsupportedEncodingException, FileNotFoundException {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            // make the current directory where the
            // file is stored
            File dir = new File("./");
            dir.mkdirs();
            File tmp = new File(dir, fileName);
            tmp.createNewFile();
            // now that the file has been made, write to it
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
