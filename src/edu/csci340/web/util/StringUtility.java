package edu.csci340.web.util;

public class StringUtility {
    public static String formatHostString(String str) {
        return str.substring(0, str.indexOf('/'));
    }

    public static String formatFileString(String str) {
        return str.substring(str.indexOf('/'), str.length());
    }

    public static String getMethod(String str) {
        return str.split(" ")[0];
    }

    public static String getUrl(String str) {
        return str.split(" ")[1];
    }

    public static String[] getUrlParamaters(String str) {
        return str.split(" ")[1].split("/");
    }


    public static String notFoundEntityBody() {
        return "<HTML>" + "<HEAD><TITLE>Not Found</TITLE><style>.bottom{position:absolute;bottom:0;}</style></HEAD>" + "<BODY><center><img class='bottom'/></center></BODY></HTML>";
    }
}
