package com.example.controllers.nfControllers;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.Alerts;
import com.example.model.parameters;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class driverController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField carPlateField;

    @FXML 
    private TextField companyNameField;

    @FXML
    private TextField telephoneField;

    @FXML
    private void handleSaveButton() {
        if (new parameters().parametersDriverRegister(cpfField, carPlateField, nameField, companyNameField, telephoneField)) {
            String name = nameField.getText();
            String cpf = cpfField.getText();
            String cpf2 = cpf.substring(0,3).concat(".").concat(cpf.substring(3,6)).concat(".").concat(cpf.substring(6,9)).concat("-").concat(cpf.substring(9,11));
            String carPlate = carPlateField.getText();
            String carPlate2 = carPlate.toUpperCase();
            String companyName = companyNameField.getText();
            String telephone = telephoneField.getText();
            String telephone2 = "(" + telephone.substring(0, 2) + ")" + telephone.substring(2, 7) + "-" + telephone.substring(7, 11);
            if (new DatabaseConnection().driverRegister(cpf2, carPlate2, name, companyName, telephone2)) {
                nameField.clear();
                cpfField.clear();
                carPlateField.clear();
                companyNameField.clear();
                telephoneField.clear();
                new Alerts().showAlertInformation("Motorista cadastrado com sucesso", null);
            } else {
                new Alerts().showAlertWarning("Ocorreu um erro ao cadastrar o motorista no banco de dados.\n Verifique se os campos estão corretos, ou esse CPF já está cadastrado.", null);
            }
        } 
    }
}