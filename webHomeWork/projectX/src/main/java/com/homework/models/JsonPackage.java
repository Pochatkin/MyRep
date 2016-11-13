package com.homework.models;

/**
 * Created by mikhail on 13.09.16.
 */
public class JsonPackage {
    private String action;
    private String[] docsName;
    private String[] textInDocs;

    public JsonPackage(String action, String[] docsName, String[] textInDocs) {
        this.action = action;
        this.docsName = docsName;
        this.textInDocs = textInDocs;
    }

    public JsonPackage(String action) {
        this.action = action;
    }
}
