package com.exigenservices.lectures.api.exception;

/**
 * User Management Process Exception
 * Created by bolbin on 23.11.2014.
 */
public class UserManagementException extends Exception {

    private static final long serialVersionUID = 2123596318906116023L;

    /**
     * Creates a new instance of <code>UserManagementException</code> without detail message.
     */
    public UserManagementException() {
    }

    /**
     * Constructs an instance of <code>UserManagementException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UserManagementException(String msg) {
        super(msg);
    }
}
