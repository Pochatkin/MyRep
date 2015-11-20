package com.exigenservices.lectures.model;

import java.io.Serializable;

/**
 * Bookmark
 * Created by bolbin
 * on 07.11.2014.
 */
public class Bookmark implements Serializable{

    private static final long serialVersionUID = -2945844528912688113L;

    private String id;
    private String value;

    /**
     * Class constructor
     * @param id bookmark id
     * @param value name of bookmark
     */
    public Bookmark(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String value) {
        this.value = value;
    }

}
