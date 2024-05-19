package com.example.controllers;

import java.io.IOException;

import com.example.App;

import javafx.fxml.FXML;

public class mainController {

    @FXML
    private void handleNFAction() throws IOException {
        App.setRoot(App.getNF());
    }
    @FXML
    private void handleVisitanteAction() throws IOException {
        App.setRoot(App.getVisitor());
    }
    @FXML
    private void handleCadastroAction() throws IOException {
        App.setRoot(App.getCadastro());
    }
    @FXML
    private void handleEdicaoAction() throws IOException {
        App.setRoot(App.getEditUser());
    }
    @FXML
    private void handleLogoutAction() throws IOException {
        App.setRoot(App.getLogin());
    }


    

    

}