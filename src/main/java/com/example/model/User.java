package com.example.model;

import java.util.regex.Pattern;

import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class User {

    public String Name;
    public String username;
    protected String Password;
    public accesType role;

    public String getName() {
        return Name;
    }

    public accesType getRole() {
        return role;
    }
    
    public String getUsername() {
        return username;
    }

    public User() {

    }

    public User(String Name, String Password, accesType role) {
        this.Name = Name;
        this.Password = Password;
        this.role = role;
    }

    public boolean loginParameters(TextField nameField, PasswordField passwordField, TextField userField, PasswordField confirmPassField, String password, String confirmPass, @SuppressWarnings("rawtypes") ComboBox menuButton) {
        if (nameField.getText().trim().isEmpty() || userField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || confirmPassField.getText().trim().isEmpty() || (menuButton.getValue() == null)) {
            new Alerts().showAlertWarning("Todos os campos devem ser preenchidos.");
        } else if (!isValidPassword(password)) {
            new Alerts().showAlertWarning("A senha deve conter pelo menos 6 caracteres, incluindo maiúsculas, minúsculas e números.");
        } else if (!password.equals(confirmPass)) {
            new Alerts().showAlertWarning("As senhas não coincidem.");
        } else {
            new Alerts().showAlertError("Falha ao cadastrar o usuário.");
        }
        return false;
    }

    private boolean isValidPassword(String pass) {
        return pass.length() >= 6 && Pattern.compile("[a-z]").matcher(pass).find()
                && Pattern.compile("[A-Z]").matcher(pass).find()
                && Pattern.compile("[0-9]").matcher(pass).find();
    }


}


