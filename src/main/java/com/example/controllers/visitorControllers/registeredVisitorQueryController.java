package com.example.controllers.visitorControllers;

import java.io.IOException;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.Alerts;
import com.example.model.Pages;
import com.example.model.visitor;
import com.example.model.Dialog.Dialogs;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("rawtypes")
public class registeredVisitorQueryController {

    DatabaseConnection dbAccess = new DatabaseConnection();
    Pages Pages = new Pages();
    visitor visitor;
    Alerts alerts = new Alerts();


    @FXML
    private TableColumn visitorNameColumn; 

    @FXML
    private TableColumn cpfColumn; 

    @FXML
    private TableColumn companyNameColumn;

    @FXML
    private TableView<visitor> visitorQueryTable; 
 
    @FXML
    private void initialize() {
        loadTableData();  
        doubleClicked();
    }
    
    @SuppressWarnings("unchecked")
    private void loadTableData() {    
        ObservableList<visitor> data = dbAccess.fillTableVisitorsRegistered();
        visitorQueryTable.setItems(data);

        
        visitorNameColumn.setCellValueFactory(new PropertyValueFactory<>("visitorName"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("visitorCPF"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));


        if(visitorQueryTable.getColumns().isEmpty()) {
            visitorQueryTable.getColumns().addAll(visitorNameColumn, cpfColumn, companyNameColumn);
        }
    }

    @FXML
    private void handleRegisterButton() throws IOException {
        Pages.getVisitorRegister();
    }

    @FXML
    private void handleEditButtonAction() {
        visitor = visitorQueryTable.getSelectionModel().getSelectedItem();
        if (visitor != null) {
            Dialogs dialog = new Dialogs();
            cpf = visitor.getVisitorCPF();
            cpf = cpf.replace(".", "").replace("-", "");
            dialog.showEditVisitorDialog(visitor.getVisitorName(), visitor.getCompanyName(), cpf);
            System.out.println(visitor.getVisitorCPF());
            loadTableData();
        } else alerts.showAlertWarning("Parece que você não selecionou nenhum registro", null);
    }
    public static String cpf;

    private void doubleClicked() {
        visitorQueryTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    visitor = visitorQueryTable.getSelectionModel().getSelectedItem();
                    if (visitor != null) {
                        try {
                            cpf = visitor.getVisitorCPF();
                            cpf = cpf.replace(".", "").replace("-", "");
                            Pages.getEnterVisitor();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}
