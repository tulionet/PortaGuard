package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static String pages = "login/login"; // Variável global

    public static String getLogin() {
        return pages = "login/login";
    }

    public static String getMain() {
        return pages = "main/main";
    }

    public static String getCadastro() {
        return pages = "users/cadastro";
    }

    public static String getEditUser() {
        return pages = "users/editUser";
    }

    public static String getNF() {
        return pages = "users/editUser";
    }

    public static String getVisitor() {
        return pages = "users/editUser";
    }

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(pages), 1366, 768);

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}