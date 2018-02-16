package edu.csci340.web.service;

import edu.csci340.web.EndSystem;

// will do stuff in the future...
public class URLValidation implements EndSystem {
    private Request request;

    public URLValidation(Request request) {
        this.request = request;
    }

    public boolean isValidHost() {
        String url = request.getHost();
        return true;
    }

    public boolean isValidRequest() {
        String url = request.getRequest();
        return true;
    }

}
