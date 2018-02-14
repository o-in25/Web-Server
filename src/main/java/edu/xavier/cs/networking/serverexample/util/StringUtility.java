package edu.xavier.cs.networking.serverexample.util;

import java.io.InputStream;

/**
 * Created by o_in25 on 2/13/18.
 */
public class StringUtility {

    public static String formatHostString(String str) {
        return str.substring(0, str.indexOf('/'));
    }

    public static String formatFileString(String str) {
        return str.substring(str.indexOf('/'), str.length());
    }

    public static String remoeveHeader(String str) {
        for(int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
        }
        return str;
    }



}
