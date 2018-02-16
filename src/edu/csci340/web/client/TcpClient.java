package edu.csci340.web.client;

import edu.csci340.web.EndSystem;
import edu.csci340.web.service.Request;
import edu.csci340.web.util.FileDumper;
import edu.csci340.web.util.StringUtility;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient implements EndSystem {
    // for the file
    private String newFile;
    // parts of url
    private String url;
    // server stuff
    private int port;
    private Socket server;
    // for the request
    private String type = null;
    private Request request;
    // some big enoygh size
    private final int MAX_BUFFER_SIZE = 1000;


    // this configures the the tcp client
    // to make the proper request, that being
    // head get post and delete
    public TcpClient(String type, String url, String newFile, final int port) {
        this.url = url;
        this.newFile = newFile;
        this.port = port;
        this.server = null;
        this.type = type;
        // set the request
        request = new Request(type, url);

    }


    // handles teh different type of requests
    // since get and head are so similar
    // the same method can be calle
    public void delegate() throws IOException {
        if(type != null) {
            if(type.equalsIgnoreCase("GET")) {
                // get
                get(false);
            } else if(type.equalsIgnoreCase("HEAD")) {
                get(true);
            } else if(type.equalsIgnoreCase("DELETE")) {
                // delete functionality for future...
            } else if(type.equalsIgnoreCase("POST")) {
                // post functionality for future...
            } else {
                System.out.println("Invalid...");
            }
        }
    }

    // executes the get request
    // also handles the head request which is the same
    public void get(boolean isHead) throws IOException {
        // get the request string
        String requestAsString = request.getRequest();
        // set up server
        server = new Socket(StringUtility.formatHostString(url), port);
        InputStreamReader serverInputStreamReader = new InputStreamReader(server.getInputStream());
        DataOutputStream serverDataOutputStream = new DataOutputStream(server.getOutputStream());
        BufferedReader fromServer = new BufferedReader(serverInputStreamReader);
        // write
        serverDataOutputStream.writeBytes(requestAsString);
        // buffer of arbitrary size
        char[] buffer = new char[MAX_BUFFER_SIZE];
        int i = 0;
        // uses string builder to
        // correctly concatenate the data
        String data = "";
        StringBuilder mutableString = new StringBuilder(data);
        System.out.println("Connecting...");
        while((i = fromServer.read(buffer, 0, buffer.length)) > -1) {
            // append it
            mutableString.append(String.valueOf(buffer));
        }
        // this is where the get/head types are established
        // since head is so similar to get, all that is required
        // is to change the output
        if(isHead) {
            data = mutableString.toString();
        } else {
            // get the index of crlf
            data = mutableString.toString().substring(String.valueOf(buffer).indexOf(crlf + crlf));
        }
        System.out.println("RESPONSE => " + crlf + data);
        FileDumper.outToFile(newFile, data);
    }

}