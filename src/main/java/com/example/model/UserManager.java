package com.example.model;

public class UserManager {
    public static User currentUser;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }
}
