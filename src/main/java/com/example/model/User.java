package com.example.model;

public class User {

    private String Name;
    private  String username;
    protected String Password;
    private accesType role;
    private int id;

    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }

    public accesType getRole() {
        return role;
    }
    
    public String getUsername() {
        return username;
    }

    public User() {

    }

    public User(int id, String Name, String username, String Password, accesType role) {
        this.id = id;
        this.Name = Name;
        this.Password = Password;
        this.role = role;
        this.username = username;
    }
}


