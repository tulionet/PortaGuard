package com.example.controllers.visitorControllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.Alerts;
import com.example.model.visitor;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("rawtypes")
public class exitVisitorController {
    DatabaseConnection dbAccess = new DatabaseConnection();
    Alerts alert = new Alerts();
    visitor visitor;


    @FXML
    private TableColumn entranceHourColumn; 

    @FXML
    private TableColumn visitorNameColumn; 

    @FXML
    private TableColumn cpfColumn;

    @FXML
    private TableColumn companyNameColumn;

    @FXML
    private TableColumn reasonColumn;

    @FXML
    private TableColumn userColumn;

    @FXML
    private TableView<visitor> visitorQueryTable; 
 
    @FXML
    private void initialize() {
        loadTableData();  
        doubleClicked();
    }
    
    @SuppressWarnings("unchecked")
    private void loadTableData() {    
        ObservableList<visitor> data = dbAccess.fillTableVisitors(1);
        visitorQueryTable.setItems(data);

        System.out.println(data);
        
        entranceHourColumn.setCellValueFactory(new PropertyValueFactory<>("entranceHour"));
        visitorNameColumn.setCellValueFactory(new PropertyValueFactory<>("visitorName"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("visitorCPF"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));



        if(visitorQueryTable.getColumns().isEmpty()) {
            visitorQueryTable.getColumns().addAll(entranceHourColumn, visitorNameColumn, cpfColumn, companyNameColumn, reasonColumn, userColumn);
        } else {
            visitorQueryTable.getColumns().clear();
            visitorQueryTable.getColumns().addAll(entranceHourColumn, visitorNameColumn, cpfColumn, companyNameColumn, reasonColumn, userColumn);
        }
    }

    private void exitVisitor() {
        visitor = visitorQueryTable.getSelectionModel().getSelectedItem();
        if (visitor != null) {
            List<ButtonType> buttonTypes = Arrays.asList(ButtonType.YES, ButtonType.NO);
            alert.showAlertConfirmation("Deseja dar baixa no visitante?", buttonTypes);
            if (alert.getResult() == ButtonType.YES) {
                dbAccess.exitVisitor(visitor.getVisitorCPF());
                alert.showAlertInformation("Visitante baixado com sucesso!", null);
                loadTableData();
            } else {
                alert.showAlertInformation("Operação cancelada!", null);
            }
        }
    }

    @FXML
    private void handleRegisterButton() throws IOException {
        exitVisitor();
    }

    private void doubleClicked() {
        visitorQueryTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    exitVisitor();
                }
            }
        });
    }
}
