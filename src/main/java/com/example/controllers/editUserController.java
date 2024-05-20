package com.example.controllers;

import java.io.IOException;

import com.example.App;
import com.example.controllers.Database.DatabaseConnection;
import com.example.model.userDisplay;

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
    private void handleNFAction() throws IOException {
        App.setRoot(App.getNF());
    }
    @FXML
    private void handleVisitanteAction() throws IOException {
        App.setRoot(App.getVisitor());
    }
    @FXML
    private void handleCadastroAction() throws IOException {
        App.setRoot(App.getCadastro());
    }
    @FXML
    private void handleEdicaoAction() throws IOException {
        App.setRoot(App.getEditUser());
    }
    @FXML
    private void handleLogoutAction() throws IOException {
        App.setRoot(App.getLogin());
    }

    //----------------------------------------------------------------------

    @FXML
    private TableColumn nameColumn; 

    @FXML
    private TableColumn userColumn; 

    @FXML
    private TableColumn roleColumn; 

    @FXML
    private TableColumn<userDisplay, Boolean> checkBoxColumn;

    @FXML
    private TableView<userDisplay> editUserTable; 

    @FXML
    private void initialize() {
        loadTableData();  


    }
    
    @FXML
    private void handleEditButtonAction() throws IOException {
        
    }

    @FXML
    private void handleExcludeButton() throws IOException{

    }

    
    DatabaseConnection dbAccess = new DatabaseConnection();
    ObservableList<userDisplay> data = dbAccess.fillTableUsers();

    @FXML 
    private void handleCheckOnClick() throws IOException{
        // userDisplay Selected = editUserTable.getSelectionModel().getSelectedItem();
        // if (Selected != null) {
        //     Selected.setSelected(true);
        //     editUserTable.refresh();
        // }
    }
    
    @SuppressWarnings("unchecked")
    private void loadTableData() {
        // DatabaseConnection dbAccess = new DatabaseConnection();
        // ObservableList<userDisplay> data = dbAccess.fillTableUsers();

   
    
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
        
        
        

        // Define o valor da célula
        checkBoxColumn.setCellValueFactory(new Callback<CellDataFeatures<userDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<userDisplay, Boolean> param) {
                userDisplay userDisplay = param.getValue();
        

                return userDisplay.selectedProperty();
            }
        });


        // checkBoxColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        // checkBoxColumn.setCellFactory(column -> {
        //     CheckBoxTableCell<userDisplay, Boolean> cell = new CheckBoxTableCell<>();
        //     cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> event.consume());
        //     return cell;
        // });

        // checkBoxColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());

        // editUserTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        //     if (newSelection != null) {
        //         Platform.runLater(() -> editUserTable.getSelectionModel().clearSelection());
        //     }
        // });
        editUserTable.setItems(data);


        userColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        if(editUserTable.getColumns().isEmpty()) {
            editUserTable.getColumns().addAll(userColumn, nameColumn, roleColumn, checkBoxColumn);
        }
    }
}