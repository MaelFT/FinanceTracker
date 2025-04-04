package com.example.demo.controller;

import com.example.demo.db.IncomeDAO;
import com.example.demo.model.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class AddIncomeLineController {

    @FXML
    private TableView<Income> table;

    private ObservableList<Income> items = FXCollections.observableArrayList();

    public void initialize() {
        items.addAll(IncomeDAO.allIncomes());
        table.setItems(items);
    }

    @FXML
    private void addLine() throws IOException {
        URL fxmlLocation = getClass().getResource("/com/example/demo/formIncome.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("FXML file not found.");
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        NewIncome newIncome = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Add Income");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        Income income = newIncome.getIncome();
        if (income != null) {
            table.getItems().add(income);
            IncomeDAO incomeDAO = new IncomeDAO();
            incomeDAO.save(income);
        }
    }

    public void setTable(List<Income> incomes) {
        TableColumn<Income, String> periodeColumn = new TableColumn<>("Période");
        periodeColumn.setCellValueFactory(new PropertyValueFactory<>("periode"));

        TableColumn<Income, Float> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<Income, Float> salaireColumn = new TableColumn<>("Salaire");
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));

        TableColumn<Income, Float> aidesColumn = new TableColumn<>("Aides");
        aidesColumn.setCellValueFactory(new PropertyValueFactory<>("aides"));

        TableColumn<Income, Float> autoEntrepriseColumn = new TableColumn<>("Auto-entreprise");
        autoEntrepriseColumn.setCellValueFactory(new PropertyValueFactory<>("autoEntreprise"));

        TableColumn<Income, Float> revenusPassifsColumn = new TableColumn<>("Revenus Passifs");
        revenusPassifsColumn.setCellValueFactory(new PropertyValueFactory<>("revenusPassifs"));

        TableColumn<Income, Float> autresColumn = new TableColumn<>("Autres");
        autresColumn.setCellValueFactory(new PropertyValueFactory<>("autres"));

        table.getColumns().clear();
        table.getColumns().addAll(
                periodeColumn, totalColumn, salaireColumn, aidesColumn,
                autoEntrepriseColumn, revenusPassifsColumn, autresColumn
        );

        items.addAll(incomes);
        table.setItems(FXCollections.observableArrayList(incomes));
    }
}
