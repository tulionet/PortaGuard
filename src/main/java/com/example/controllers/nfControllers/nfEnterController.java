package com.example.controllers.nfControllers;

import java.io.IOException;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.parameters;
import com.example.model.Dialog.Dialogs;
import com.example.model.Alerts;
import com.example.model.Driver;
import com.example.model.User;
import com.example.model.UserManager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class nfEnterController {

    Alerts alert = new Alerts();
    Dialogs dialog = new Dialogs();
    DatabaseConnection db = new DatabaseConnection();
    Driver driver;
    
    @FXML
    private TextField CpfField;

    @FXML
    private TextField DriverPlateField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField CompanyField;

    @FXML
    private TextField NfField;

    @FXML
    private TextField TelField;

    @FXML
    private CheckBox EnterCheck;

    @FXML
    private CheckBox ExitCheck;


    @FXML
    private void handleEnterCheck() throws IOException {
        
        if (EnterCheck.isSelected()) {
            ExitCheck.setSelected(false);
        }
    }

    @FXML
    private void handleExitCheck() throws IOException {
        if (ExitCheck.isSelected()) {
            EnterCheck.setSelected(false);
        }
    }

    private void filterDriverByCPF(String cpf) {
        cpf = cpf.substring(0,3).concat(".").concat(cpf.substring(3,6)).concat(".").concat(cpf.substring(6,9)).concat("-").concat(cpf.substring(9,11));
        if ((driver = db.filterDriverByCPF(cpf)) != null) {
            DriverPlateField.setText(driver.getCarPlate());
            NameField.setText(driver.getName());
            CompanyField.setText(driver.getCompanyName());
            TelField.setText(driver.getTelephone());
        }else {
            dialog.showDriverRegisterDialog(); 
        } 
    }
    @FXML
    private void initialize() {
        Platform.runLater(() -> {
        String cpf = driverQueryController.cpf;
        if(cpf != null) {
            CpfField.setText(cpf);
            driverQueryController.cpf = null;
            filterDriverByCPF(cpf);
        }
             CpfField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) {
                    if (!CpfField.getText().isEmpty()) {
                        filterDriverByCPF(CpfField.getText());
                    }
            }}); 
        });
    }
    

    @FXML
    private void handleSaveButton() throws IOException {
        if (new parameters().ParametersEnterNF(CpfField, DriverPlateField, NameField, CompanyField, NfField, TelField, (EnterCheck.isSelected() ? EnterCheck : ExitCheck))) {
            String cpf = CpfField.getText();
            cpf = cpf.substring(0,3).concat(".").concat(cpf.substring(3,6)).concat(".").concat(cpf.substring(6,9)).concat("-").concat(cpf.substring(9,11));
            String nf = NfField.getText();
            String situacao = (EnterCheck.isSelected()) ? "Entrada" : "Saída";
            User user = UserManager.getUser();
            if (new DatabaseConnection().nfEnter(cpf, nf, situacao, user.getId())) {
                alert.showAlertInformation("Nota Fiscal cadastrada com sucesso como: " + situacao, null);
                CpfField.clear();
                DriverPlateField.clear();
                NameField.clear();
                CompanyField.clear();
                NfField.clear();
                TelField.clear();
                EnterCheck.setSelected(false);
                ExitCheck.setSelected(false);
            } else {
                alert.showAlertWarning("Não foi possível inserir a nota fiscal no banco de dados", null);
            }
        }
    }
}
