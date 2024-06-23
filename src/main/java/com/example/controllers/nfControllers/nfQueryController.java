package com.example.controllers.nfControllers;

import java.io.IOException;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.nf;
import com.example.model.Dialog.DialogCallbackNF;
import com.example.model.Dialog.Dialogs;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("rawtypes")
public class nfQueryController implements DialogCallbackNF {


    @FXML
    private TableColumn datahoraColumn; 

    @FXML
    private TableColumn situacaoColumn; 

    @FXML
    private TableColumn nfColumn; 

    @FXML
    private TableColumn driverNameColumn;
    
    @FXML
    private TableColumn cpfColumn;

    @FXML
    private TableColumn companyNameColumn;

    @FXML
    private TableColumn driverPlateColumn;

    @FXML
    private TableColumn telephoneColumn;

    @FXML
    private TableColumn userColumn;

    @FXML
    private TableView<nf> nfQueryTable; 

    DatabaseConnection dbAccess = new DatabaseConnection();
    Dialogs dialog = new Dialogs();

    ObservableList<nf> data = dbAccess.fillTableNF();
    @FXML
    private void initialize() {
        loadTableData(data);  
    }
    
    @SuppressWarnings("unchecked")
    private void loadTableData(ObservableList<nf> data) {    

        nfQueryTable.setItems(data);
        
        datahoraColumn.setCellValueFactory(new PropertyValueFactory<>("datahora"));
        situacaoColumn.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        nfColumn.setCellValueFactory(new PropertyValueFactory<>("nf"));
        driverNameColumn.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("driverCPF"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        driverPlateColumn.setCellValueFactory(new PropertyValueFactory<>("driverPlate"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        if(nfQueryTable.getColumns().isEmpty()) {
            nfQueryTable.getColumns().addAll(datahoraColumn, situacaoColumn, nfColumn, driverNameColumn, cpfColumn, companyNameColumn, driverPlateColumn, telephoneColumn, userColumn);
        }
    }

    @FXML
    private void handleFilterButton() throws IOException {
        dialog.setCallbackNF(this);;
        dialog.showNfFilterDialog();
    }
    @Override
    public void onDataReceived(ObservableList<nf> data) {
        loadTableData(data);
    }

}
