package com.example.demo.controller;

import com.example.demo.model.Expense;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NewExpense extends Dialog<Expense> {

    @FXML
    private TextField periodeField;

    @FXML
    private TextField logementField;

    @FXML
    private TextField nourritureField;

    @FXML
    private TextField sortiesField;

    @FXML
    private TextField transportField;

    @FXML
    private TextField voyageField;

    @FXML
    private TextField impotField;

    @FXML
    private TextField autresField;

    private Expense expense;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    public NewExpense() {}

    @FXML
    private void create() {
        float logement = Float.parseFloat(logementField.getText());
        float nourriture = Float.parseFloat(nourritureField.getText());
        float sorties = Float.parseFloat(sortiesField.getText());
        float transport = Float.parseFloat(transportField.getText());
        float voyage = Float.parseFloat(voyageField.getText());
        float impot = Float.parseFloat(impotField.getText());
        float autres = Float.parseFloat(autresField.getText());

        float total = logement + nourriture + sorties + transport + voyage + impot + autres;

        expense = new Expense(
                periodeField.getText(),
                total,
                logement,
                nourriture,
                sorties,
                transport,
                voyage,
                impot,
                autres
        );

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    public void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Expense getExpense() {
        return expense;
    }
}
