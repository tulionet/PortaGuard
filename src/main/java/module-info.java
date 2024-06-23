module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    

    opens com.example to javafx.fxml;
    opens com.example.controllers to javafx.fxml;
    opens com.example.controllers.adminControllers to javafx.fxml;
    opens com.example.controllers.nfControllers to javafx.fxml;
    opens com.example.controllers.visitorControllers to javafx.fxml;
    opens com.example.model to javafx.fxml, javafx.base;

    
    exports com.example;
}
