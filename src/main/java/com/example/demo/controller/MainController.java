package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML private StackPane screenContainer;

    @FXML
    public void initialize() {
        loadScreen("dashboard.fxml");
    }

    public void loadScreen(String fxmlFile) {
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("/com/example/demo/" + fxmlFile));
            screenContainer.getChildren().setAll(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
