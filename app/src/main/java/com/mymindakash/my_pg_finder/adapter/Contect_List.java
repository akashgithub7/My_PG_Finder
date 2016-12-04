package com.mymindakash.my_pg_finder.adapter;

/**
 * Created by Vikram on 12/4/2016.
 */

public class Contect_List {
    private int id;
    private String name;
    private String password;
    private int number;
    private String email;

    public Contect_List() {
    }

    public Contect_List(int id, String name, String password, int number, String email) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
