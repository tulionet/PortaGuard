package com.example.model;

public enum accesType {
    NORMAL_USER(1), 
    ADMINISTRATOR(2);

    private final int value;

    accesType(int value) {
        this.value = value;
    }

    public int getType() {
        return value;
    }
}
