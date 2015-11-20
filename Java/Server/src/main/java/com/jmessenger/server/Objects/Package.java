package com.jmessenger.server.Objects;

/**
 * Created by Михаил on 14.11.2015.
 */
public class Package {
    private String message;
    private String senderLogin;
    private String recipientLogin;
    private String date;
    private byte[] file;


    public void Package(String message, String senderLogin, String recipientLogin, byte[] file, String date) {
        this.message = message;
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientLogin;
        this.file = file;
        this.date = date;
    }

    public String getRecipientLogin() {
        return recipientLogin;
    }


    public byte[] getFile() {

        return file;
    }


    public String getSenderLogin() {

        return senderLogin;
    }


    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }


}

