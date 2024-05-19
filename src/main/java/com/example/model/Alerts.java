package com.example.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Alerts {

        public void showAlertError(String message) {
            showAlert(AlertType.ERROR, message);
        }
    
        public void showAlertConfirmation(String message) {
            showAlert(AlertType.CONFIRMATION, message);
        }

        public void showAlertInformation(String message) {
            showAlert(AlertType.INFORMATION, message);
        }

        public void showAlertWarning(String message) {
            showAlert(AlertType.WARNING, message);
        }

    
        private void showAlert(AlertType alertType, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
}
