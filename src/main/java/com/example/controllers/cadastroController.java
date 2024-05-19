package com.example.controllers;

import java.io.IOException;

import com.example.App;
import com.example.model.Alerts;
import com.example.model.User;
import com.example.model.accesType;
import com.example.controllers.Database.DatabaseConnection;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class cadastroController {

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

    //-------------------------------------------------------------------

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField confirmPassField;

    @FXML
    private ComboBox<accesType> menuButton;
    
    @FXML
    private void initialize() {
        menuButton.getItems().addAll(accesType.values());
    }

    @FXML
    private void handleSaveButtonAction() throws IOException {
        String name = nameField.getText();
        String password = passwordField.getText();
        String user = userField.getText();
        String confirmPass = confirmPassField.getText();
        int type = menuButton.getValue().getType();
        User param = new User();
        DatabaseConnection conn = new DatabaseConnection();
        param.loginParameters(nameField, passwordField, userField, confirmPassField, password, confirmPass, menuButton);
            if (conn.userRegister(name, user, password, type)) {
                new Alerts().showAlertConfirmation("Usuário cadastrado com sucesso!");

                nameField.clear();
                userField.clear();
                passwordField.clear();
                confirmPassField.clear();
            } else {
                new Alerts().showAlertError("Falha ao cadastrar o usuário.");
            }
    }
}
