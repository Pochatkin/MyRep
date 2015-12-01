package com.example.julia.uley.common;

import java.io.Serializable;

/**
 * Created by Julia on 23.11.2015.
 */
public class Pass implements Serializable {
    public Pass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private String pass;
}