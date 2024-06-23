package com.example.model;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class Alerts {

        public void showAlertError(String message, List<ButtonType> buttonTypes) {
            showAlert(AlertType.ERROR, message, buttonTypes);
        }
    
        public void showAlertConfirmation(String message, List<ButtonType> buttonTypes) {
            showAlert(AlertType.CONFIRMATION, message, buttonTypes);
        }

        public void showAlertInformation(String message, List<ButtonType> buttonTypes) {
            showAlert(AlertType.INFORMATION, message, buttonTypes);
        }

        public void showAlertWarning(String message, List<ButtonType> buttonTypes) {
            showAlert(AlertType.WARNING, message, buttonTypes);
        }

        private static ButtonType result;

        private void showAlert(AlertType alertType, String message, List<ButtonType> buttonTypes) {
            Alert alert = new Alert(alertType);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText(message);

            if (buttonTypes != null) {
                alert.getButtonTypes().setAll(buttonTypes);
            }
            
            alert.showAndWait();
            result = alert.getResult();
        }

        public Object getResult() {
            return result;
        }
}
