package com.example.model;

import javafx.beans.property.SimpleStringProperty;

public class Driver {
    private SimpleStringProperty name;
    private SimpleStringProperty cpf; 
    private SimpleStringProperty carPlate; 
    private SimpleStringProperty companyName; 
    private SimpleStringProperty telephone;
    
    public Driver() {
        this.name = new SimpleStringProperty("");
        this.cpf = new SimpleStringProperty("");
        this.carPlate = new SimpleStringProperty("");
        this.companyName = new SimpleStringProperty("");
        this.telephone = new SimpleStringProperty("");
    }
    
    
    public Driver(String name, String cpf, String carPlate, String companyName, String telephone) {
        this.name = new SimpleStringProperty(name);
        this.cpf = new SimpleStringProperty(cpf);
        this.carPlate = new SimpleStringProperty(carPlate);
        this.companyName = new SimpleStringProperty(companyName);
        this.telephone = new SimpleStringProperty(telephone);
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getCpf() {
        return cpf.get();
    }
    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }
    public String getCarPlate() {
        return carPlate.get();
    }
    public void setCarPlate(String carPlate) {
        this.carPlate.set(carPlate);
    }
    public String getCompanyName() {
        return companyName.get();
    }
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    public String getTelephone() {
        return telephone.get();
    }
    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }
    
}