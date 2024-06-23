package com.example.controllers.nfControllers;

import java.io.IOException;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.Driver;
import com.example.model.Pages;
import com.example.model.Dialog.Dialogs;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
@SuppressWarnings("rawtypes")
public class driverQueryController {

    private DatabaseConnection dbAccess = new DatabaseConnection();
    private Dialogs dialog = new Dialogs();
    private Pages Pages = new Pages();


    @FXML
    private TableColumn telephoneColumn; 

    @FXML
    private TableColumn driverNameColumn; 

    @FXML
    private TableColumn cpfColumn; 

    @FXML
    private TableColumn carPlateColumn;
    
    @FXML
    private TableColumn companyNameColumn;

    @FXML
    private TableView<Driver> driverQueryTable; 

    @FXML
    private void initialize() {
        loadTableData();  
        doubleClicked();
    }
    
    @SuppressWarnings("unchecked")
    private void loadTableData() {    
        ObservableList<Driver> data = dbAccess.fillTableDrivers();
        driverQueryTable.setItems(data);
        
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        driverNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        carPlateColumn.setCellValueFactory(new PropertyValueFactory<>("carPlate"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));


        if(driverQueryTable.getColumns().isEmpty()) {
            driverQueryTable.getColumns().addAll(telephoneColumn, driverNameColumn, cpfColumn, carPlateColumn, companyNameColumn);
        }
    }

    @FXML
    private void handleRegisterButton() throws IOException {
        Pages.getDriverRegister();
    }

    @FXML
    private void handleEditButtonAction() {
        Driver driver = driverQueryTable.getSelectionModel().getSelectedItem();
        if (driver != null) {
            dialog.showEditDriverDialog(driver.getName(), driver.getTelephone(), driver.getCarPlate(), driver.getCompanyName(), driver.getCpf());
            loadTableData();
        }
    }
    public static String cpf;

    private void doubleClicked() {
        driverQueryTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    Driver driver = driverQueryTable.getSelectionModel().getSelectedItem();
                    if (driver != null) {
                        try {
                            cpf = driver.getCpf();
                            cpf = cpf.replace(".", "").replace("-", "");
                            Pages.getEnterNF();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}   


