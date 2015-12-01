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

    public Dialog (Login _login, String _lastMessage){
        login = _login;
        lastMessage = _lastMessage;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
