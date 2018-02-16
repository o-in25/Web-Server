package edu.csci340.web.service;

import edu.csci340.web.util.StringUtility;

// represents the string that
// is the request
public class Request {
    private String host;
    private String file;
    private String type;
    private String request;
    private final String crlf = "\r\n";

    // makes a new request
    public Request(String type, String url) {
        // format the strings
        host = StringUtility.formatHostString(url);
        file = StringUtility.formatFileString(url);
        this.type = type;
        this.request = format();
        System.out.println("REQUEST TYPE => " + crlf + request);
    }



    // will take the information and
    // build a url with it
    private String format() {
        String request;
        request = type + " " + file + " HTTP/1.0" + crlf;
        request += "Host: " + host + crlf;
        request += "Connection: close" + crlf;
        request += crlf;
        return request;
    }


    public String getRequest() {return request;}

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCrlf() {
        return crlf;
    }








}
