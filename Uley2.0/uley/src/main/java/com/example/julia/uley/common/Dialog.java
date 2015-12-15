package com.example.julia.uley.common;

/**
 * Created by Julia on 20.11.2015.
 */
public class Dialog {
    private Login login;
    private String lastMessage;

    public Dialog(){
        this(null,null);
    }

    public Dialog (Login login, String lastMessage){
        this.login = login;
        this.lastMessage = lastMessage;
    }

    public Login getLogin() {
        return login;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
