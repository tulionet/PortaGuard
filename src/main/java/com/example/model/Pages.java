package com.example.model;

import java.io.IOException;

import com.example.App;

public class Pages {

    public void getLogin() throws IOException {
        App.setRoot("login/login");
    }

    public void getMain() throws IOException {
        App.setRoot("main/main");
    }

    public void getCadastro() throws IOException {
        App.setRoot("users/userRegister");
    }

    public void getEditUser() throws IOException {
        App.setRoot("users/editUser");
    }

    public void getNF() throws IOException {
        App.setRoot("nf/nf");
    }

    public void getVisitor() throws IOException {
        App.setRoot("visitor/visitor");
    }

    public void getEnterNF() throws IOException {
        App.setRoot("nf/enterNF");
    }

    public void getDriverRegister() throws IOException {
        App.setRoot("nf/driverRegister");
    }

    public void getDriverQuery() throws IOException {
        App.setRoot("nf/driverQuery");
    }

    public void getNFQuery() throws IOException {
        App.setRoot("nf/nfQuery");
    }   

    public void getEnterVisitor() throws IOException {
        App.setRoot("visitor/enterVisitor");
    }   

    public void getExitVisitor() throws IOException {
        App.setRoot("visitor/exitVisitor");
    }   

    public void getVisitorRegister() throws IOException {
        App.setRoot("visitor/visitorRegister");
    }   

    public void getRegisteredVisitorQuery() throws IOException {
        App.setRoot("visitor/registeredVisitorQuery");
    }   

    public void getVisitorQuery() throws IOException {
        App.setRoot("visitor/visitorQuery");
    }   


    
    
}
