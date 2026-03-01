package com.example.tallerbicicletas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Parent root = fxmlLoader.load();   // ← AQUÍ está la clave

        Scene scene = new Scene(root);
        stage.setTitle("Bienvenido");
        stage.setScene(scene);
        stage.sizeToScene();   // ajusta la ventana al contenido
        stage.setResizable(false); // opcional
        stage.show();
    }
}