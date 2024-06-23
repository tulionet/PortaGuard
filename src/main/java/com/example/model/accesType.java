package com.example.model;

public enum accesType {
    ADMINISTRATOR(1),
    NORMAL_USER(2);


    private final int value;

    accesType(int value) {
        this.value = value;
    }

    public int getType() {
        return value;
    }


}
