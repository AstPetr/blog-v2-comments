package models;

import java.io.Serializable;

/**
 * Created by Asta on 2016-10-20.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 6297385302078200511L;

    private String name;
    private String password;
    private String email;
    private int id;

    public void setName(String name) {

        this.name = name;
    }

    public void setPassword(String password) {

        this.password = password;
    }


    public void setEmail(String email) {

        this.email = email;
    }


    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public String getPassword() {

        return password;
    }

    public String getEmail() {

        return email;
    }

    public int getId() {

        return id;
    }

    @Override
    public String toString() {
        return "Name=" + this.name + ", Email=" + this.email;
    }
}

