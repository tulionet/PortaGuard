package com.example.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class nf {
    private SimpleStringProperty datahora;
    private SimpleStringProperty situacao;
    private SimpleDoubleProperty nf;
    private SimpleStringProperty driverName;
    private SimpleStringProperty driverCPF;
    private SimpleStringProperty companyName;
    private SimpleStringProperty driverPlate;
    private SimpleStringProperty telephone;
    private SimpleStringProperty user;

    public String getDatahora() {
        return datahora.get();
    }
    public void setDatahora(SimpleStringProperty datahora) {
        this.datahora = datahora;
    }
    public String getSituacao() {
        return situacao.get();
    }
    public void setSituacao(SimpleStringProperty situacao) {
        this.situacao = situacao;
    }
    public String getNf() {
        return String.format("%.0f", nf.get());
    }
    public void setNf(SimpleDoubleProperty nf) {
        this.nf = nf;
    }
    public String getDriverName() {
        return driverName.get();
    }
    public void setDriverName(SimpleStringProperty driverName) {
        this.driverName = driverName;
    }
    public String getDriverCPF() {
        return driverCPF.get();
    }
    public void setDriverCPF(SimpleStringProperty driverCPF) {
        this.driverCPF = driverCPF;
    }
    public String getCompanyName() {
        return companyName.get();
    }
    public void setCompanyName(SimpleStringProperty companyName) {
        this.companyName = companyName;
    }
    public String getDriverPlate() {
        return driverPlate.get();
    }
    public void setDriverPlate(SimpleStringProperty driverPlate) {
        this.driverPlate = driverPlate;
    }
    public String getTelephone() {
        return telephone.get();
    }
    public void setTelephone(SimpleStringProperty telephone) {
        this.telephone = telephone;
    }
    public String getUser() {
        return user.get();
    }
    public void setUser(SimpleStringProperty user) {
        this.user = user;
    }
    public nf(String datahora, String situacao, Double nf,
            String driverName, String driverCPF, String companyName,
            String driverPlate, String telephone, String user) {
        this.datahora = new SimpleStringProperty(datahora);
        this.situacao = new SimpleStringProperty(situacao);
        this.nf = new SimpleDoubleProperty(nf);
        this.driverName = new SimpleStringProperty(driverName);
        this.driverCPF = new SimpleStringProperty(driverCPF);
        this.companyName = new SimpleStringProperty(companyName);
        this.driverPlate = new SimpleStringProperty(driverPlate);
        this.telephone = new SimpleStringProperty(telephone);
        this.user = new SimpleStringProperty(user);
    }

    
}