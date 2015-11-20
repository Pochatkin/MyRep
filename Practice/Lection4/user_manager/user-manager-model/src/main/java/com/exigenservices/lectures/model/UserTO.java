package com.exigenservices.lectures.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User Transfer Object
 *
 * Created by bolbin on 23.11.2014.
 */
public class UserTO implements Serializable{

    private static final long serialVersionUID = -2310882902018760564L;

    private Long   id;
    private String name;
    private Date dateOfBirth;

    public UserTO(Long id, String name, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
