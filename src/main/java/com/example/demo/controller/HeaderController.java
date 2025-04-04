package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class HeaderController {

    @FXML
    private ComboBox<String> menuSelect;

    private MainController mainController;

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    @FXML
    public void handleSelection() {
        String selected = menuSelect.getValue();
        if (selected == null || mainController == null) return;

        switch (selected) {
            case "Tableau de bord" -> mainController.loadScreen("dashboard.fxml");
            case "Dépenses" -> mainController.loadScreen("table-view.fxml");
        }
    }
}
