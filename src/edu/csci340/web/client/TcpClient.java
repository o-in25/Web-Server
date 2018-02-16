package edu.csci340.web.client;

import edu.csci340.web.EndSystem;
import edu.csci340.web.util.FileDumper;
import edu.csci340.web.util.StringUtility;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient implements EndSystem{
    private String newFile;
    private String url;
    // parts of url
    private String file;
    private String host;
    // socket stuff
    private int port;
    private Socket server;
    private final int MAX_BUFFER_SIZE = 1000;


    public TcpClient(String url, String newFile, final int port) {
        this.url = url;
        this.newFile = newFile;
        this.port = port;
        this.server = null;
        this.host = StringUtility.formatHostString(url);
        this.file = StringUtility.formatFileString(url);
    }

    public void outToFile() throws IOException {
        String request;
        server = new Socket(StringUtility.formatHostString(url), port);
        InputStreamReader serverInputStreamReader = new InputStreamReader(server.getInputStream());
        DataOutputStream serverDataOutputStream = new DataOutputStream(server.getOutputStream());
        BufferedReader fromServer = new BufferedReader(serverInputStreamReader);
        request = "GET " + file + " HTTP/1.0" + crlf;
        request += "Host: "+ host + crlf;
        request += "Connection: close" + crlf;
        request += crlf;
        serverDataOutputStream.writeBytes(request);
        char[] buffer = new char[MAX_BUFFER_SIZE];
        int i = 0;
        String data = "";
        StringBuilder mutableString = new StringBuilder(data);
        while((i = fromServer.read(buffer, 0, buffer.length)) > -1) {
            mutableString.append(String.valueOf(buffer));
        }
        data = mutableString.toString().substring(String.valueOf(buffer).indexOf(crlf + crlf));
        System.out.println("CONTENT => " + data);
        FileDumper.outToFile(newFile, data);
    }

}