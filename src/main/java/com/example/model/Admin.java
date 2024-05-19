package com.example.model;

public class Admin extends User{

    public Admin(String Name, String Password, int role) {
        super(Name, Password, accesType.ADMINISTRATOR);
    }

}
