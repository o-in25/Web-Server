package edu.csci340.web.server;


        import edu.csci340.web.EndSystem;
        import edu.csci340.web.util.StringUtility;

        import java.io.*;
        import java.net.Socket;


public final class HttpServer implements Runnable, EndSystem {
    private Socket client;
    private final String crlf = "\r\n";




    public HttpServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            processRequest();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            preventDefault();
        }
    }


    // where most of the processing will take place...
    private void processRequest() throws Exception {
        InputStreamReader clientInputStreamReader = new InputStreamReader(client.getInputStream());
        DataOutputStream clientDataOutputStream = new DataOutputStream(client.getOutputStream());
        BufferedReader fromClient = new BufferedReader(clientInputStreamReader);
        // get the request
        String request = fromClient.readLine();
        String requestHeader = null;
        // read each line of the header
        while ((requestHeader = fromClient.readLine()).length() != 0) {

            System.out.println(requestHeader + crlf);

        }
        System.out.println("REQUEST => " + request);
        String method = StringUtility.getMethod(request);
        String url = StringUtility.getUrl(request);
        url = "." + url;
        // check to see if the url is valid...
        boolean validFile = true;
        FileInputStream readUrl = null;
        try {
            readUrl = new FileInputStream(url);
        } catch (FileNotFoundException e) {
            validFile = false;
        } finally {

        }
        // the three parts of the request
        // here we format them...
        String statusLine, contentTypeLine, entityBody = null;
        if (validFile) {
            statusLine = "HTTP/1.0 200 OK" + crlf;
            contentTypeLine = "Content-Type: " + contentType(url) + crlf;
        } else {
            statusLine = "HTTP/1.0 440 Not Found" + crlf;
            contentTypeLine = "ContentType: text/html" + crlf;
            entityBody = StringUtility.notFoundEntityBody();
        }
        // write status...
        clientDataOutputStream.writeBytes(statusLine);
        // write content type...
        clientDataOutputStream.writeBytes(contentTypeLine);
        // separate the lines...
        clientDataOutputStream.writeBytes(crlf);
        // send the entity body...
        if (validFile) {
            sendBytes(readUrl, clientDataOutputStream, 1025);
            clientInputStreamReader.close();
        } else {
            clientDataOutputStream.writeBytes(entityBody);
        }
        clientDataOutputStream.close();
        fromClient.close();
    }


    // send the information to the client
    private static void sendBytes(FileInputStream readUrl, OutputStream clientDataOutputStream, int bufferSize) throws Exception{
        byte[] buffer = new byte[bufferSize];
        int bytes = 0;
        while((bytes = readUrl.read(buffer)) != -1) {
            // Copy requested file into the socket's output stream.
            clientDataOutputStream.write(buffer, 0 , bytes);
        }
    }


    private static String contentType(String fileName) {
        if(fileName.endsWith(".htm") || fileName.endsWith(".html")) {
            return "text/html";
        }
        else if (fileName.endsWith(".gif")) {
            return "image/jpeg";
        }
        else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/gif";
        }
        return "application/octet-stream";
    }



    private void preventDefault() {

    }
}
