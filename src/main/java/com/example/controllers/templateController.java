package com.example.controllers;

import java.io.IOException;

import com.example.model.Alerts;
import com.example.model.UserManager;
import com.example.model.accesType;
import com.example.model.Pages;

import javafx.fxml.FXML;

public class templateController {
//-----------------------------------------------------------
//--------------------HEADER---------------------------------------------
    Pages pages = new Pages();
    UserManager user;
    Alerts alerts = new Alerts();
    
    private void adminPages(int i) throws IOException {
        if ((user.getUser().getRole().equals(accesType.ADMINISTRATOR))) {
            
            if (i == 1) {
                pages.getCadastro();
            }
            else if (i == 2) {
                pages.getEditUser();
            }
        } else {
            alerts.showAlertWarning("Você não tem permissão para acessar esta página", null);
        }
    }

    @FXML
    private void handleNFAction() throws IOException {
        pages.getNF();
    }
    @FXML
    private void handleVisitanteAction() throws IOException {
        pages.getVisitor();
    }
    @FXML
    private void handleCadastroAction() throws IOException {
        adminPages(1);
    }
    @FXML
    private void handleEdicaoAction() throws IOException {
        adminPages(2);
    }
    @FXML
    private void handleLogoutAction() throws IOException {
        pages.getLogin();
    }
//-----------------------------------------------------------
//--------------------LATERAL NF---------------------------------------------
    @FXML
    private void handleEnterNFAction() throws IOException {
        pages.getEnterNF();
    }
    @FXML
    private void handleDriverRegisterAction() throws IOException {
        pages.getDriverRegister();
    }
    @FXML
    private void handleDriverQueryAction() throws IOException {
        pages.getDriverQuery();
    }
    @FXML
    private void handleNFQueryAction() throws IOException {
        pages.getNFQuery();
    }
//-----------------------------------------------------------
//--------------------LATERAL VISITANTE---------------------------------------------
    @FXML
    private void handleEnterVisitorAction() throws IOException {
        pages.getEnterVisitor();
    }
    @FXML
    private void handleExitVisitorAction() throws IOException {
        pages.getExitVisitor();
    }
    @FXML
    private void handleVisitorRegisterAction() throws IOException {
        pages.getVisitorRegister();
    }
    @FXML
    private void handleRegisteredVisitorQueryAction() throws IOException {
        pages.getRegisteredVisitorQuery();
    }
    @FXML
    private void handlevVisitorQueryAction() throws IOException {
        pages.getVisitorQuery();
    }
}
