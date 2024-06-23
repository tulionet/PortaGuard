package com.example.controllers.visitorControllers;

import java.io.IOException;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.visitor;
import com.example.model.Dialog.DialogCallbackVisitor;
import com.example.model.Dialog.Dialogs;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("rawtypes")
public class visitorQueryController implements DialogCallbackVisitor {

    @FXML
    private TableColumn entranceHourColumn; 

    @FXML
    private TableColumn exitHourColumn; 

    @FXML
    private TableColumn visitorNameColumn; 

    @FXML
    private TableColumn visitorCPFColumn;
    
    @FXML
    private TableColumn companyNameColumn;

    @FXML
    private TableColumn reasonColumn;

    @FXML
    private TableColumn userColumn;

    @FXML
    private TableView<visitor> visitorQueryTable; 

    DatabaseConnection dbAccess = new DatabaseConnection();
    Dialogs dialog = new Dialogs();
    ObservableList<visitor> data = dbAccess.fillTableVisitors(0);

    @FXML
    private void initialize() {
        loadTableData(data);  
    }
    
    @SuppressWarnings("unchecked")
    private void loadTableData(ObservableList<visitor> data) {    

        visitorQueryTable.setItems(data);
        
        entranceHourColumn.setCellValueFactory(new PropertyValueFactory<>("entranceHour"));
        exitHourColumn.setCellValueFactory(new PropertyValueFactory<>("exitHour"));
        visitorNameColumn.setCellValueFactory(new PropertyValueFactory<>("visitorName"));
        visitorCPFColumn.setCellValueFactory(new PropertyValueFactory<>("visitorCPF"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        if(visitorQueryTable.getColumns().isEmpty()) {
            visitorQueryTable.getColumns().addAll(entranceHourColumn, exitHourColumn, visitorNameColumn, visitorCPFColumn, companyNameColumn, reasonColumn, userColumn);
        } else {
            visitorQueryTable.getColumns().clear();
            visitorQueryTable.getColumns().addAll(entranceHourColumn, exitHourColumn, visitorNameColumn, visitorCPFColumn, companyNameColumn, reasonColumn, userColumn);
        }
    }

    @FXML
    private void handleFilterButton() throws IOException {
        dialog.setCallbackVisitor(this);
        dialog.showVisitorFilterDialog();
    }

    @Override
    public void onDataReceived(ObservableList<visitor> data) {
        loadTableData(data);
    }




}
