package com.example.controllers;

import java.io.IOException;

import com.example.model.Alerts;
import com.example.model.Pages;
import com.example.controllers.Database.DatabaseConnection;

import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class loginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        DatabaseConnection db = new DatabaseConnection();
        if (db.auth(username, password)) { // autentica o usuário
            new Pages().getMain(); // redirecionamento para a tela principal
        } else {
            new Alerts().showAlertError("Falha na autenticação. Verifique suas credenciais.", null);
        }
    }

}
