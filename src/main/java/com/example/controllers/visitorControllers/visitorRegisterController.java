package com.example.controllers.visitorControllers;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.Alerts;
import com.example.model.parameters;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class visitorRegisterController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField cpfField;

    @FXML 
    private TextField companyNameField;

    @FXML
    private void handleSaveButton() {
        if (new parameters().parametersVisitorRegister(cpfField, nameField, companyNameField)) {
            String name = nameField.getText();
            String cpf = cpfField.getText();
            String cpf2 = cpf.substring(0,3).concat(".").concat(cpf.substring(3,6)).concat(".").concat(cpf.substring(6,9)).concat("-").concat(cpf.substring(9,11));
            String companyName = companyNameField.getText();
            new DatabaseConnection().visitorRegister(cpf2, name, companyName);
            nameField.clear();
            cpfField.clear();
            companyNameField.clear();
            new Alerts().showAlertInformation("Visitante cadastrado com sucesso", null);
        } else {
            new Alerts().showAlertWarning("Ocorreu um erro ao cadastrar o visitante", null);
        }
    }
}
