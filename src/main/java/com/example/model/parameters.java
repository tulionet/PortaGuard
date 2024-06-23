package com.example.model;

import java.util.regex.Pattern;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class parameters {

    public boolean loginParameters(TextField nameField, PasswordField passwordField, TextField userField, PasswordField confirmPassField, String password, String confirmPass, @SuppressWarnings("rawtypes") ComboBox menuButton) {
        if (nameField.getText().trim().isEmpty() || userField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || confirmPassField.getText().trim().isEmpty() || (menuButton.getValue() == null)) {
            new Alerts().showAlertWarning("Todos os campos devem ser preenchidos.", null);
            return false;
        } else if (!isValidPassword(password)) {
            new Alerts().showAlertWarning("A senha deve conter pelo menos 6 caracteres, incluindo maiúsculas, minúsculas e números.", null);
            return false;
        } else if (!password.equals(confirmPass)) {
            new Alerts().showAlertWarning("As senhas não coincidem.", null);
            return false;
        } else
        return true;
    }

    public boolean editUser1(TextField nameField, PasswordField passwordField, String password, ComboBox menuButton) {
        if (nameField.getText().trim().isEmpty() || (menuButton.getValue() == null)) {
            new Alerts().showAlertWarning("Nome não pode ser vazio!", null);
            return false;
        } else if (!isValidPassword(password)) {
            new Alerts().showAlertWarning("A senha deve conter pelo menos 6 caracteres, incluindo maiúsculas, minúsculas e números.", null);
            return false;
        }
        return true;
    }

    public boolean editUser2(TextField nameField, ComboBox menuButton) {
        if (nameField.getText().trim().isEmpty() || (menuButton.getValue() == null)) {
            new Alerts().showAlertWarning("Nome não pode ser vazio!", null);
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String pass) {
        return pass.length() >= 6 && Pattern.compile("[a-z]").matcher(pass).find()
                && Pattern.compile("[A-Z]").matcher(pass).find()
                && Pattern.compile("[0-9]").matcher(pass).find();
    }

    public boolean ParametersEnterNF(TextField cpfField, TextField carPlateField, TextField nameField, TextField companyField, TextField nfField, TextField telField, CheckBox situacao) {
        if (cpfField.getText().trim().isEmpty() || carPlateField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || companyField.getText().trim().isEmpty() || nfField.getText().trim().isEmpty() || telField.getText().trim().isEmpty() || !situacao.isSelected()) {
            new Alerts().showAlertWarning("Todos os campos devem ser preenchidos.", null);
            return false;
        } else if (!(cpfField.getText().length() == 11) || !(carPlateField.getText().length() == 7)) {
            new Alerts().showAlertWarning("Formato do CPF ou placa do carro inválida.", null);
            return false;
        } else if (nfField.getText().contains("a-z")) {
            new Alerts().showAlertWarning("Campo de Nota Fiscal não aceita letras.", null);
            return false;
        } else if (!(telField.getText().length() == 14)) {
            new Alerts().showAlertWarning("Formato do telefone está inválido.", null);
            return false;
        }
        return true;
    }

    public boolean parametersEnterVisitor(TextField cpfField, TextField visitorNameField, TextField companyNameField) {
        if (cpfField.getText().trim().isEmpty() || visitorNameField.getText().trim().isEmpty() || companyNameField.getText().trim().isEmpty()) {
            new Alerts().showAlertWarning("Todos os campos devem ser preenchidos.", null);
            return false;
        } else if (!(cpfField.getText().length() == 11)) {
            new Alerts().showAlertWarning("Formato do CPF inválido.", null);
            return false;
        } else if (cpfField.getText().contains("a-z")) {
            new Alerts().showAlertWarning("Campo de CPF não aceita letras.", null);
            return false;
        }
        return true;
    }

    public boolean parametersVisitorRegister(TextField cpfField, TextField visitorNameField, TextField companyNameField) {
        if (cpfField.getText().trim().isEmpty() || visitorNameField.getText().trim().isEmpty() || companyNameField.getText().trim().isEmpty()) {
            new Alerts().showAlertWarning("Todos os campos devem ser preenchidos.", null);
            return false;
        } else if (!(cpfField.getText().length() == 11)) {
            new Alerts().showAlertWarning("Formato do CPF inválido.", null);
            return false;
        } else if (cpfField.getText().contains("a-z")) {
            new Alerts().showAlertWarning("Campo de CPF não aceita letras.", null);
            return false;
        }
        return true;
    }

    public boolean parametersEditVisitor(TextField visitorNameField, TextField companyNameField, TextField cpfField) {
        if (visitorNameField.getText().trim().isEmpty() || companyNameField.getText().trim().isEmpty() || cpfField.getText().trim().isEmpty()) {
            new Alerts().showAlertWarning("Todos os campos devem ser preenchidos.", null);
            return false;
        } else if (!(cpfField.getText().length() == 11)) {
            new Alerts().showAlertWarning("Formato do CPF inválido.", null);
            return false;
        } else if (cpfField.getText().contains("a-z") || cpfField.getText().contains("A-Z")) {
            new Alerts().showAlertWarning("Campo de CPF não aceita letras maiúsculas.", null);
            return false;
        }
        return true;
    }

    public boolean parametersDriverRegister(TextField cpfField, TextField carPlateField, TextField nameField, TextField companyField, TextField telField) {
        if (cpfField.getText().trim().isEmpty() || carPlateField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || companyField.getText().trim().isEmpty() || telField.getText().trim().isEmpty()) {
            new Alerts().showAlertWarning("Todos os campos devem ser preenchidos.", null);
            return false;
        } else if (!(cpfField.getText().length() == 11) || !(carPlateField.getText().length() == 7)) {
            new Alerts().showAlertWarning("Formato do CPF ou placa do carro inválida.", null);
            return false;
        } else if (!(telField.getText().length() == 11) || !telField.getText().matches("\\d+")) {
            new Alerts().showAlertWarning("Formato do telefone está inválido.", null);
            return false;
        } else if (cpfField.getText().contains("a-z")) {
            new Alerts().showAlertWarning("Campo de CPF não aceita letras.", null);
            return false;
        }
        return true;
    }

    public boolean parametersEditDriver(TextField carPlateField, TextField companyField, TextField telField) {
        if (carPlateField.getText().trim().isEmpty() || companyField.getText().trim().isEmpty() || telField.getText().trim().isEmpty()) {
            new Alerts().showAlertWarning("Todos os campos devem ser preenchidos.", null);
            return false;
        } else if (!(carPlateField.getText().length() == 7)) {
            new Alerts().showAlertWarning("Placa do carro inválida.", null);
            return false;
        } else if (!(telField.getText().length() == 14)) {
            new Alerts().showAlertWarning("Formato do telefone está inválido.", null);
            return false;
        }
        return true;
    }
}
