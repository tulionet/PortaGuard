package com.example.controllers.adminControllers;

import java.io.IOException;

import com.example.controllers.Database.DatabaseConnection;
import com.example.model.userDisplay;
import com.example.model.Dialog.Dialogs;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.Callback;

@SuppressWarnings("rawtypes")
public class editUserController {

    @FXML
    private TableColumn nameColumn; 

    @FXML
    private TableColumn userColumn; 

    @FXML
    private TableColumn roleColumn; 

    @FXML
    private TableColumn enableColumn;

    @FXML
    private TableColumn<userDisplay, Boolean> checkBoxColumn;

    @FXML
    private TableView<userDisplay> editUserTable; 

    DatabaseConnection dbAccess = new DatabaseConnection();
    
    @FXML
    private void initialize() {
        loadTableData();  
    }
    
    @SuppressWarnings("unchecked")
    private void loadTableData() {    
        ObservableList<userDisplay> data = dbAccess.fillTableUsers();
//lógica checkbox-------------------------------
        
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
        checkBoxColumn.setCellFactory(tc -> {
            CheckBoxTableCell<userDisplay, Boolean> cell = new CheckBoxTableCell<>();
            cell.setOnMouseClicked(e -> {
                if (! cell.isEmpty()) {
                    userDisplay item = (userDisplay) cell.getTableRow().getItem();
                    item.setSelected(!item.isSelected());
                    editUserTable.refresh();
                }
            });
            return cell ;
        });

        
        checkBoxColumn.setCellValueFactory(new Callback<CellDataFeatures<userDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<userDisplay, Boolean> param) {
                userDisplay userDisplay = param.getValue();
        

                return userDisplay.selectedProperty();
            }
        });
//----------------------------------------
        editUserTable.setItems(data);
        
        userColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        enableColumn.setCellValueFactory(new PropertyValueFactory<>("isEnable"));


        if(editUserTable.getColumns().isEmpty()) {
            editUserTable.getColumns().addAll(userColumn, nameColumn, roleColumn, checkBoxColumn);
        }
    }

    @FXML
    private void handleEnableButton() throws IOException {
        for(userDisplay us : editUserTable.getItems()) {
            if (us.isSelected() && !us.getUsuario().equals("admin")) {
                dbAccess.enableDisableUser(us.getUsuario(), us.getIsEnable());  
                us.setIsEnable(us.getIsEnable().equals("SIM") ? "NÃO" : "SIM");
            }
        }
        loadTableData();
    }

    @FXML
    private void handleEditButtonAction() throws IOException {
        for (userDisplay us : editUserTable.getItems()) {
            if (us.isSelected() && !us.getUsuario().equals("admin")) {
                Dialogs dialog = new Dialogs();
                dialog.showEditDialog(us.getNome(), us.getRole(), us.getUsuario());
            }
        }
        loadTableData();
    }
}