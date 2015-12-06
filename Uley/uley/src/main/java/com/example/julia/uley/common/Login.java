package com.example.julia.uley.common;

/**
 * Created by Julia on 23.11.2015.
 */

import java.io.Serializable;
import java.util.Objects;

public class Login implements Serializable {
    private String login;

    public Login() {
        this(null);
    }

    public Login(String login) {
        this.login = login;
    }


    @Override
    public String toString() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Login) {
            return Objects.equals(login, ((Login) obj).login);
        }
        return false;
    }


    public String getLogin() {
        return login;
    }
}