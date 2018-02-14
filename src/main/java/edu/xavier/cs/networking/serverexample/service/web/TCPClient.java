package edu.xavier.cs.networking.serverexample.service.web;

import edu.xavier.cs.networking.serverexample.util.FileRouter;
import edu.xavier.cs.networking.serverexample.util.StringUtility;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.nio.charset.MalformedInputException;

public class TCPClient {
    private String file = null;
    private String host = null;
    private String destination = null;
    private final String eol = "\r\n";
    private String fileName = null;
    private final int MAX_BUFFER_SIZE = 10000;
    // objects
    private Socket clientSocket = null;
    private BufferedReader bufferedReader = null;
    private DataOutputStream dataOutputStream = null;


    public TCPClient(String url, String destination, String fileName, final int port) throws IOException {
        // www.cs.xu.edu/dir/dir/file.html
        // get the host name
        // www.cs.xu.edu
        host = StringUtility.formatHostString(url);
        // get the file from the host
        file = StringUtility.formatFileString(url);
        // new socket
        clientSocket = new Socket(host, port);
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        // set the destination
        this.destination = destination;
        this.fileName = fileName;
    }


    public void dumpToFile(final int bufferSize) throws IOException, OutOfMemoryError {
        if(bufferSize < 0 || bufferSize > MAX_BUFFER_SIZE) {
            throw new OutOfMemoryError();
        } else {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            // the request that
            // will be sent
            String queryRequest;
            queryRequest = "GET " + file + " HTTP/1.0" + eol;
            queryRequest += "Host: "+ host + eol;
            queryRequest += "Connection: close" + eol;
            queryRequest += eol;
            outToServer.writeBytes(queryRequest);
            char[] buffer = new char[bufferSize];
            int i = 0;
            String fileAsString = "";
            while((i = bufferedReader.read(buffer, 0, buffer.length)) > -1) {
                fileAsString += String.valueOf(buffer);
            }
            clientSocket.close();
            FileRouter.outToFile(destination + "/" + fileName, "UTF-8", fileAsString);
        }
    }
}
