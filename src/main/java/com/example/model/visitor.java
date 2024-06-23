package com.example.model;

import javafx.beans.property.SimpleStringProperty;

public class visitor {
    private SimpleStringProperty entranceHour;
    private SimpleStringProperty exitHour;
    private SimpleStringProperty visitorName;
    private SimpleStringProperty visitorCPF;
    private SimpleStringProperty companyName;
    private SimpleStringProperty reason;
    private SimpleStringProperty user;
    
    public visitor() {
        this.entranceHour = new SimpleStringProperty("");
        this.exitHour = new SimpleStringProperty("");
        this.visitorName = new SimpleStringProperty("");
        this.visitorCPF = new SimpleStringProperty("");
        this.companyName = new SimpleStringProperty("");
        this.reason = new SimpleStringProperty("");
        this.user = new SimpleStringProperty("");
    }
    
    public visitor(String entranceHour, String exitHour,
        String visitorName, String visitorCPF, String companyName,
        String reason, String user) {
        this.entranceHour = new SimpleStringProperty(entranceHour);
        this.exitHour = new SimpleStringProperty(exitHour);
        this.visitorName = new SimpleStringProperty(visitorName);
        this.visitorCPF = new SimpleStringProperty(visitorCPF);
        this.companyName = new SimpleStringProperty(companyName);
        this.reason = new SimpleStringProperty(reason);
        this.user = new SimpleStringProperty(user);
    }

    
    public visitor(String visitorName, String visitorCPF, String companyName) {
        this.visitorName = new SimpleStringProperty(visitorName);
        this.visitorCPF = new SimpleStringProperty(visitorCPF);
        this.companyName = new SimpleStringProperty(companyName);
    }

    public String getEntranceHour() {
        return entranceHour.get();
    }
    public void setEntranceHour(String entranceHour) {
        this.entranceHour.set(entranceHour);
    }
    public String getExitHour() {
        return exitHour.get();
    }
    public void setExitHour(String exitHour) {
        this.exitHour.set(exitHour);
    }
    public String getVisitorName() {
        return visitorName.get();
    }
    public void setVisitorName(String visitorName) {
        this.visitorName.set(visitorName);
    }
    public String getVisitorCPF() {
        return visitorCPF.get();
    }
    public void setVisitorCPF(String visitorCPF) {
        this.visitorCPF.set(visitorCPF);
    }
    public String getCompanyName() {
        return companyName.get();
    }
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    public String getReason() {
        return reason.get();
    }
    public void setReason(String reason) {
        this.reason.set(reason);
    }
    public String getUser() {
        return user.get();
    }
    public void setUser(String user) {
        this.user.set(user);
    }
}


