package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login/login"), 1366, 768); // Carrega a tela de login
        stage.setMaximized(true); // Maximizar o window
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException { // Método que troca a tela
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException { // Método que carrega a tela
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}