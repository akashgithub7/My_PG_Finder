package com.mymindakash.my_pg_finder.adapter;

/**
 * Created by Vikram on 12/4/2016.
 */

public class Contect_List {
    private int id;
    private String name;
    private String number;
    private String email;
    private String password;


    public Contect_List() {
    }

    public Contect_List(int id, String name, String password, String number, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.number = number;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
