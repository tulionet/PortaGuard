package com.example.model.Dialog;

import java.io.IOException;
import java.net.URL;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.Alerts;
import com.example.model.accesType;
import com.example.model.nf;
import com.example.model.Pages;
import com.example.model.parameters;
import com.example.model.visitor;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;

import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@SuppressWarnings("rawtypes")
public class Dialogs {
    parameters param = new parameters();
    DatabaseConnection db = new DatabaseConnection();
    Alerts alert = new Alerts();
    Pages Pages = new Pages();
    
    private DialogCallbackNF callbackNF;
    public void setCallbackNF(DialogCallbackNF callbackNF) {
        this.callbackNF = callbackNF;
    }
    public void fetchDataNF(ObservableList<nf> data) {
        if (callbackNF != null) {
            callbackNF.onDataReceived(data);
        }
    }

    private DialogCallbackVisitor callbackVisitor;
    public void setCallbackVisitor(DialogCallbackVisitor callbackVisitor) {
        this.callbackVisitor = callbackVisitor;
    }
    public void fetchDataVisitor(ObservableList<visitor> data) {
        if (callbackVisitor != null) {
            callbackVisitor.onDataReceived(data);
        }
    }

    private void setStylesheet(Dialog dialog) {
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/com/example/dialog/stylesheet.css").toExternalForm());
    }

    public void showEditDialog(String nameUser, String roleUser, String usernameUser) {
        Dialog<ButtonType> dialog = new Dialog<>();

        setStylesheet(dialog);

        dialog.setTitle("Editar Usuário");
        VBox vbox = new VBox();
        TextField nameField = new TextField();
        PasswordField passwordField = new PasswordField();

        ComboBox roleField = new ComboBox<accesType>();

        roleField.getItems().addAll(accesType.values());

        nameField.setPromptText("Nome");
        passwordField.setPromptText("Senha");

        nameField.setText(nameUser);
        roleField.setValue(roleUser);

        vbox.getChildren().addAll(nameField, passwordField, roleField);
        dialog.getDialogPane().setContent(vbox);
        vbox.setSpacing(10);
        ButtonType editButtonType = new ButtonType("Editar", ButtonData.FINISH);
        ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(editButtonType, cancelButtonType);
        Button editButton = (Button) dialog.getDialogPane().lookupButton(editButtonType);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);

        vbox.getStyleClass().add("vbox");
        editButton.getStyleClass().add("editar");
        cancelButton.getStyleClass().add("cancelar");
        nameField.getStyleClass().add("txtField");
        passwordField.getStyleClass().add("txtField");
        roleField.getStyleClass().add("roleBox");

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButtonType) {
                System.out.println(roleField.getValue());
                System.out.println( accesType.valueOf(roleField.getValue().toString()).getType());


                
                if (!passwordField.getText().isEmpty()) {
                    if (param.editUser1(nameField, passwordField, passwordField.getText(), roleField)) {
                        db.editUser(nameField.getText(), usernameUser, passwordField.getText(), accesType.valueOf(roleField.getValue().toString()).getType());
                        alert.showAlertConfirmation("Usuário alterado com sucesso!", null);  
                    }
                } else {
                    if (param.editUser2(nameField, roleField)) {
                        db.editUser2(nameField.getText(), usernameUser, accesType.valueOf(roleField.getValue().toString()).getType());
                        alert.showAlertConfirmation("Usuário alterado com sucesso!", null);  
                    }
                }

            }
            return null;
        });
        
        dialog.showAndWait();
    }

    public void showDriverRegisterDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        setStylesheet(dialog);
        dialog.setTitle("Confirmação");
        dialog.setContentText("Parece que não foi encontrado um motorista cadastrado com esse CPF. \nDeseja cadastra-lo?");
        
        ButtonType buttonTypeYes = new ButtonType("Sim", ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Não", ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes,buttonTypeNo);
        Button yesButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeYes);
        Button noButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeNo);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeYes) {
                try {
                    Pages.getDriverRegister();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
        yesButton.getStyleClass().add("bt");
        noButton.getStyleClass().add("bt");
        dialog.showAndWait();
    }

    public void showEditDriverDialog(String name, String telephone, String carPlate, String companyName, String cpf) {
        Dialog<ButtonType> dialog = new Dialog<>();
        setStylesheet(dialog);
        dialog.setTitle("Editar Motorista");
        VBox vbox = new VBox();
        TextField nameField = new TextField();
        Label telephoneLabel = new Label("Telefone");
        TextField telephoneField = new TextField();
        Label carPlateLabel = new Label("Placa do Veículo");
        TextField carPlateField = new TextField();
        Label companyNameLabel = new Label("Nome da Empresa");
        TextField companyNameField = new TextField();
        nameField.setText(name);
        nameField.setEditable(false);
        telephoneLabel.setLabelFor(telephoneField);
        telephoneField.setText(telephone);
        carPlateLabel.setLabelFor(carPlateField);
        carPlateField.setText(carPlate);
        companyNameLabel.setLabelFor(companyNameField);
        companyNameField.setText(companyName);
        vbox.getChildren().addAll(nameField, telephoneLabel, telephoneField, carPlateLabel, carPlateField, companyNameLabel, companyNameField);
        vbox.setSpacing(10);
        dialog.getDialogPane().setContent(vbox);
        ButtonType buttonTypeYes = new ButtonType("Sim", ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Não", ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes,buttonTypeNo);
        Button yesButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeYes);
        Button noButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeNo);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeYes) {
                if (param.parametersEditDriver(carPlateField, companyNameField, telephoneField)) {
                    db.editDriver(carPlateField.getText(), companyNameField.getText(), telephoneField.getText(), cpf);
                    alert.showAlertInformation("Motorista editado com sucesso!", null);
                }
            }
            return null;
        });
        yesButton.getStyleClass().add("bt");
        noButton.getStyleClass().add("bt");
        nameField.getStyleClass().add("txtField");
        telephoneField.getStyleClass().add("txtField");
        carPlateField.getStyleClass().add("txtField");
        companyNameField.getStyleClass().add("txtField");
        dialog.showAndWait();
    }

    public void showEditVisitorDialog(String name, String companyName, String cpf) {
        Dialog<ButtonType> dialog = new Dialog<>();
        setStylesheet(dialog);
        dialog.setTitle("Editar Visitante");
        VBox vbox = new VBox();
        Label nameLabel = new Label("Nome Visitante");
        TextField nameField = new TextField();
        Label companyNameLabel = new Label("Nome da Empresa");
        TextField companyNameField = new TextField();
        Label cpfLabel = new Label("CPF");
        TextField cpfField = new TextField();
        nameLabel.setLabelFor(nameField);
        nameField.setText(name);
        // nameField.setEditable(false);
        companyNameLabel.setLabelFor(companyNameField);
        companyNameField.setText(companyName);
        cpfLabel.setLabelFor(cpfField);
        cpfField.setText(cpf);
        cpfField.setEditable(false);
        vbox.getChildren().addAll(nameLabel, nameField, companyNameLabel, companyNameField, cpfLabel, cpfField);
        vbox.setSpacing(10);
        dialog.getDialogPane().setContent(vbox);
        ButtonType buttonTypeYes = new ButtonType("Sim", ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Não", ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes,buttonTypeNo);
        Button yesButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeYes);
        Button noButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeNo);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeYes) {
                if (param.parametersEditVisitor(nameField, companyNameField, cpfField)) {
                    String cpfValue = cpfField.getText();
                    cpfValue = cpfValue.substring(0,3).concat(".").concat(cpfValue.substring(3,6)).concat(".").concat(cpfValue.substring(6,9)).concat("-").concat(cpfValue.substring(9,11));
                    db.editVisitor(nameField.getText(), companyNameField.getText(), cpfValue);
                    alert.showAlertInformation("Visitante editado com sucesso!", null);
                }
            }
            return null;
        });
        yesButton.getStyleClass().add("bt");
        noButton.getStyleClass().add("bt");
        nameField.getStyleClass().add("txtField");
        companyNameField.getStyleClass().add("txtField");
        cpfField.getStyleClass().add("txtField");
        dialog.showAndWait();
    }
    public void showVisitorRegisterDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        setStylesheet(dialog);
        dialog.setTitle("Confirmação");
        dialog.setContentText("Parece que não foi encontrado um visitante cadastrado com esse CPF. \nDeseja cadastra-lo?");
        
        ButtonType buttonTypeYes = new ButtonType("Sim", ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Não", ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes,buttonTypeNo);
        Button yesButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeYes);
        Button noButton = (Button) dialog.getDialogPane().lookupButton(buttonTypeNo);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeYes) {
                try {
                    Pages.getVisitorRegister();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
        yesButton.getStyleClass().add("bt");
        noButton.getStyleClass().add("bt");
        dialog.showAndWait();
    }

    public void showNfFilterDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        setStylesheet(dialog);
        dialog.setTitle("Editar Motorista");
        VBox vbox = new VBox();
        Label dateLabel = new Label("Data / Hora");
        DatePicker dateFilter = new DatePicker();
        Label situacaoLabel = new Label("Situação");
        TextField situacaoFilter = new TextField();
        Label nfLabel = new Label("Número da Nota Fiscal");
        TextField nfFilter = new TextField();
        Label nameLabel = new Label("Nome Motorista");
        TextField nameFilter = new TextField();
        Label cpfLabel = new Label("CPF Motorista");
        TextField cpfFilter = new TextField();
        Label companyNameLabel = new Label("Empresa do Motorista");
        TextField companyNameFilter = new TextField();
        Label carPlateLabel = new Label("Placa do Carro");
        TextField carPlateFilter = new TextField();
        Label telephoneLabel = new Label("Telefone");
        TextField telephoneFilter = new TextField();
        Label porteiroLabel = new Label("Porteiro Responsável");
        TextField porteiroFilter = new TextField();

        dateFilter.setPromptText("Data / Hora");
        dateLabel.setLabelFor(dateFilter);
        situacaoFilter.setPromptText("Situação");
        situacaoLabel.setLabelFor(situacaoFilter);
        nfFilter.setPromptText("NF");
        nfLabel.setLabelFor(nfFilter);
        nameFilter.setPromptText("Nome");
        nameLabel.setLabelFor(nameFilter);
        cpfFilter.setPromptText("CPF");
        cpfLabel.setLabelFor(cpfFilter);
        companyNameFilter.setPromptText("Empresa");
        companyNameLabel.setLabelFor(companyNameFilter);
        carPlateFilter.setPromptText("Placa do Carro");
        carPlateLabel.setLabelFor(carPlateFilter);
        telephoneFilter.setPromptText("Telefone");
        telephoneLabel.setLabelFor(telephoneFilter);
        porteiroFilter.setPromptText("Responsável");
        porteiroLabel.setLabelFor(porteiroFilter);
        vbox.getChildren().addAll(
            dateLabel, dateFilter,
            situacaoLabel, situacaoFilter,
            nfLabel, nfFilter,
            nameLabel, nameFilter,
            cpfLabel, cpfFilter,
            companyNameLabel, companyNameFilter,
            carPlateLabel, carPlateFilter,
            telephoneLabel, telephoneFilter,
            porteiroLabel, porteiroFilter
        );
        vbox.setSpacing(10);
        dialog.getDialogPane().setContent(vbox);

        ButtonType FilterButtonType = new ButtonType("Filtrar", ButtonData.FINISH);
        ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(FilterButtonType,cancelButtonType);
        Button yesButton = (Button) dialog.getDialogPane().lookupButton(FilterButtonType);
        Button noButton = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == FilterButtonType) {
                String CPF = "";
                if (!cpfFilter.getText().isEmpty()) {
                    if (cpfFilter.getText().length() == 11) {
                        CPF = cpfFilter.getText().substring(0,3).concat(".").concat(cpfFilter.getText().substring(3,6)).concat(".").concat(cpfFilter.getText().substring(6,9)).concat("-").concat(cpfFilter.getText().substring(9,11));
                    } else {
                        CPF = cpfFilter.getText();
                    }
                }
                String telephone = "";
                if (!telephoneFilter.getText().isEmpty()) {
                    if (telephoneFilter.getText().length() == 11) {
                        telephone = telephone.concat('(' + telephoneFilter.getText().substring(0,2) + ')').concat(telephoneFilter.getText().substring(2,7)).concat("-").concat(telephoneFilter.getText().substring(7,11));
                    System.out.println(telephone);
                    } else {
                        telephone = telephoneFilter.getText();
                    }                 
                }
                ObservableList<nf> resultado = db.filterNF(dateFilter.getValue(), situacaoFilter.getText(), nfFilter.getText(), nameFilter.getText(), CPF, companyNameFilter.getText(), carPlateFilter.getText(), telephone, porteiroFilter.getText());
                fetchDataNF(resultado);
                // Imprime o resultado
                System.out.println(resultado);
            }
            
            return null;
        });
        yesButton.getStyleClass().add("bt");
        noButton.getStyleClass().add("bt");
        vbox.getStyleClass().add("vbox");
        dateFilter.getStyleClass().add("txtField");
        situacaoFilter.getStyleClass().add("txtField");
        nfFilter.getStyleClass().add("txtField");
        nameFilter.getStyleClass().add("txtField");
        cpfFilter.getStyleClass().add("txtField");
        companyNameFilter.getStyleClass().add("txtField");
        carPlateFilter.getStyleClass().add("txtField");
        telephoneFilter.getStyleClass().add("txtField");
        porteiroFilter.getStyleClass().add("txtField");
        dialog.showAndWait();
    }

    public void showVisitorFilterDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        setStylesheet(dialog);
        dialog.setTitle("Editar Visitante");
        VBox vbox = new VBox();
        Label dataEnterLabel = new Label("Data / Hora");
        DatePicker dataEnterFilter = new DatePicker();
        Label dataExitLabel = new Label("Data / Hora");
        DatePicker dataExitFilter = new DatePicker();
        Label visitorLabel = new Label("Nome Visitante");
        TextField visitorFilter = new TextField();
        Label cpfLabel = new Label("CPF Visitante");
        TextField cpfFilter = new TextField();
        Label companyNameLabel = new Label("Empresa Visitante");
        TextField companyNameFilter = new TextField();
        Label reasonLabel = new Label("Motivo");
        TextField reasonFilter = new TextField();
        Label userLabel = new Label("Porteiro Responsável");
        TextField userFilter = new TextField();

        dataEnterFilter.setPromptText("Data / Hora Entrada");
        dataEnterLabel.setLabelFor(dataEnterFilter);
        dataExitFilter.setPromptText("Data / Hora Saída");
        dataExitLabel.setLabelFor(dataExitFilter);
        visitorFilter.setPromptText("Nome do Visitante");
        visitorLabel.setLabelFor(visitorFilter);
        cpfFilter.setPromptText("CPF");
        cpfLabel.setLabelFor(cpfFilter);
        companyNameFilter.setPromptText("Empresa");
        companyNameLabel.setLabelFor(companyNameFilter);
        reasonFilter.setPromptText("Motivo");
        reasonLabel.setLabelFor(reasonFilter);
        userFilter.setPromptText("Porteiro Responsável");
        userLabel.setLabelFor(userFilter);
        vbox.getChildren().addAll(
            dataEnterLabel, dataEnterFilter,
            dataExitLabel, dataExitFilter,
            visitorLabel, visitorFilter,
            cpfLabel, cpfFilter,
            companyNameLabel, companyNameFilter,
            reasonLabel, reasonFilter,
            userLabel, userFilter
        );
        vbox.setSpacing(10);        
        dialog.getDialogPane().setContent(vbox);

        ButtonType FilterButtonType = new ButtonType("Filtrar", ButtonData.FINISH);
        ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(FilterButtonType,cancelButtonType);
        Button yesButton = (Button) dialog.getDialogPane().lookupButton(FilterButtonType);
        Button noButton = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == FilterButtonType) {
                String CPF = "";
                if (!cpfFilter.getText().isEmpty()) {
                    if (cpfFilter.getText().length() != 14) {
                        CPF = cpfFilter.getText().substring(0,3).concat(".").concat(cpfFilter.getText().substring(3,6)).concat(".").concat(cpfFilter.getText().substring(6,9)).concat("-").concat(cpfFilter.getText().substring(9,11));
                    } else {
                        CPF = cpfFilter.getText();
                    }
                }
                ObservableList<visitor> resultado = db.filterVisitor(dataEnterFilter.getValue(), dataExitFilter.getValue(), visitorFilter.getText(), CPF, companyNameFilter.getText(), reasonFilter.getText(), userFilter.getText());
                fetchDataVisitor(resultado);
                // Imprime o resultado
                System.out.println(resultado);
            }
            
            return null;
        });
        yesButton.getStyleClass().add("bt");
        noButton.getStyleClass().add("bt");
        vbox.getStyleClass().add("vbox");
        dataEnterFilter.getStyleClass().add("txtField");
        dataExitFilter.getStyleClass().add("txtField");
        visitorFilter.getStyleClass().add("txtField");
        cpfFilter.getStyleClass().add("txtField");
        companyNameFilter.getStyleClass().add("txtField");
        reasonFilter.getStyleClass().add("txtField");
        userFilter.getStyleClass().add("txtField");
        dialog.showAndWait();
    }


}


