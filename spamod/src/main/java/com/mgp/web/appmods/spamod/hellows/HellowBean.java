package com.mgp.web.appmods.spamod.hellows;

public class HellowBean {
    private  String message;

    public HellowBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HellowBean{" +
                "message='" + message + '\'' +
                '}';
    }
}

