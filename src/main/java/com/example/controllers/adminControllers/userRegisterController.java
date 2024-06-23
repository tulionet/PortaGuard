package com.example.controllers.adminControllers;

import java.io.IOException;

import com.example.model.Alerts;
import com.example.model.accesType;
import com.example.model.parameters;
import com.example.controllers.Database.DatabaseConnection;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class userRegisterController {

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
        menuButton.setValue(accesType.NORMAL_USER);
    }

    @FXML
    private void handleSaveButtonAction() throws IOException {
        parameters param = new parameters();
        DatabaseConnection conn = new DatabaseConnection();
        Alerts alert = new Alerts();

        String name = nameField.getText();
        String password = passwordField.getText();
        String user = userField.getText();
        String confirmPass = confirmPassField.getText();
        int type = menuButton.getValue().getType();

        if (param.loginParameters(nameField, passwordField, userField, confirmPassField, password, confirmPass, menuButton)) {
            if (conn.userRegister(name, user, password, type)) {
                alert.showAlertConfirmation("Usuário cadastrado com sucesso!", null);
                nameField.clear();
                userField.clear();
                passwordField.clear();
                confirmPassField.clear();
            } else {
                alert.showAlertError("Falha ao cadastrar o usuário.", null);
            }
        }
    }
}
