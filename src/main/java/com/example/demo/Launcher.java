package com.example.demo;

import com.example.demo.controller.HeaderController;
import com.example.demo.controller.MainController;
import com.example.demo.db.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("expense-view.fxml"));
        BorderPane root = loader.load();
        MainController mainController = loader.getController();

        FXMLLoader headerLoader = new FXMLLoader(getClass().getResource("header.fxml"));
        HBox headerNode = headerLoader.load();
        HeaderController headerController = headerLoader.getController();

        headerController.setMainController(mainController);

        root.setTop(headerNode);

        Database.isOK();

        Scene scene = new Scene(root, 800, 480);
        stage.setTitle("Finance Tracker!");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/demo/icons/jo.jpg")));
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
