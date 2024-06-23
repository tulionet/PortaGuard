package com.example.controllers.visitorControllers;

import java.io.IOException;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.Alerts;
import com.example.model.visitor;
import com.example.model.User;
import com.example.model.UserManager;
import com.example.model.parameters;
import com.example.model.Dialog.Dialogs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class visitorEnterController {

    Alerts alert = new Alerts();
    Dialogs dialog = new Dialogs();
    DatabaseConnection db = new DatabaseConnection();
    User user = UserManager.getUser();
    visitor visitor;

    @FXML
    private TextField CpfField;

    @FXML
    private TextField visitorNameField;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField reasonField;

    private void filterVisitorByCPF(String cpf) {
        cpf = cpf.substring(0,3).concat(".").concat(cpf.substring(3,6)).concat(".").concat(cpf.substring(6,9)).concat("-").concat(cpf.substring(9,11));
        System.out.println(cpf);
        if ((visitor = db.filterVisitorByCPF(cpf)) != null) {
            visitorNameField.setText(visitor.getVisitorName());
            companyNameField.setText(visitor.getCompanyName());

        }else {
            dialog.showVisitorRegisterDialog(); 
        } 
    }
    @FXML
    private void initialize() {
        Platform.runLater(() -> {
        String cpf = registeredVisitorQueryController.cpf;
        if(cpf != null) {
            CpfField.setText(cpf);
            registeredVisitorQueryController.cpf = null;
            filterVisitorByCPF(cpf);
            System.out.println(cpf);
        }
             CpfField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) {
                    if (!CpfField.getText().isEmpty()) {
                        filterVisitorByCPF(CpfField.getText());
                    }
            }}); 
        });
    }
    

    @FXML
    private void handleSaveButton() throws IOException {
        if (new parameters().parametersEnterVisitor(CpfField, visitorNameField, companyNameField)) {
            String cpf = CpfField.getText();
            cpf = cpf.substring(0,3).concat(".").concat(cpf.substring(3,6)).concat(".").concat(cpf.substring(6,9)).concat("-").concat(cpf.substring(9,11));
            String reason = reasonField.getText();

            if (db.visitorEnter(cpf, reason, user.getId())) {
                alert.showAlertInformation("Entrada realizada com sucesso.", null);
            } else {
                alert.showAlertWarning("Ocorreu um erro ao inserir a entrada do visitante no banco de dados.\n Verifique se os campos estão corretos, ou verifique a Saída de visitantes.", null);
            }
        }
    }
}
