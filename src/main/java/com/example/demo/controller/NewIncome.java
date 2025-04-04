package com.example.demo.controller;

import com.example.demo.model.Income;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NewIncome extends Dialog<Income> {

    @FXML
    private TextField periodeField;

    @FXML
    private TextField salaireField;

    @FXML
    private TextField aidesField;

    @FXML
    private TextField autoEntrepriseField;

    @FXML
    private TextField revenusPassifsField;

    @FXML
    private TextField autresField;

    private Income income;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    public NewIncome() {}

    @FXML
    private void create() {
        float salaire = Float.parseFloat(salaireField.getText());
        float aides = Float.parseFloat(aidesField.getText());
        float autoEntreprise = Float.parseFloat(autoEntrepriseField.getText());
        float revenusPassifs = Float.parseFloat(revenusPassifsField.getText());
        float autres = Float.parseFloat(autresField.getText());

        float total = salaire + aides + autoEntreprise + revenusPassifs + autres;

        income = new Income(
                periodeField.getText(),
                total,
                salaire,
                aides,
                autoEntreprise,
                revenusPassifs,
                autres
        );

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    public void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Income getIncome() {
        return income;
    }
}
