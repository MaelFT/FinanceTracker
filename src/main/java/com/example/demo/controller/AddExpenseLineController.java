package com.example.demo.controller;

import com.example.demo.db.ExpenseDAO;
import com.example.demo.model.Expense;
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

public class AddExpenseLineController {

    @FXML
    private TableView<Expense> table;

    private ObservableList<Expense> items = FXCollections.observableArrayList();

    public void initialize() {
        items.addAll(ExpenseDAO.allExpenses());
        table.setItems(items);
    }

    @FXML
    private void addLine() throws IOException {
        URL fxmlLocation = getClass().getResource("/com/example/demo/formExpense.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("FXML file not found.");
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        NewExpense newExpense = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Add Line");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        Expense expense = newExpense.getExpense();
        if (expense != null) {
            table.getItems().add(expense);
            ExpenseDAO expenseDAO = new ExpenseDAO();
            expenseDAO.save(expense);
        }
    }

    public void setTable(List<Expense> expenses) {
        TableColumn<Expense, String> periodeColumn = new TableColumn<>("Periode");
        periodeColumn.setCellValueFactory(new PropertyValueFactory<>("periode"));

        TableColumn<Expense, Float> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<Expense, Float> logementColumn = new TableColumn<>("Logement");
        logementColumn.setCellValueFactory(new PropertyValueFactory<>("logement"));

        TableColumn<Expense, Float> nourritureColumn = new TableColumn<>("Nourriture");
        nourritureColumn.setCellValueFactory(new PropertyValueFactory<>("nourriture"));

        TableColumn<Expense, Float> sortiesColumn = new TableColumn<>("Sorties");
        sortiesColumn.setCellValueFactory(new PropertyValueFactory<>("sorties"));

        TableColumn<Expense, Float> transportColumn = new TableColumn<>("Transport");
        transportColumn.setCellValueFactory(new PropertyValueFactory<>("transport"));

        TableColumn<Expense, Float> voyageColumn = new TableColumn<>("Voyage");
        voyageColumn.setCellValueFactory(new PropertyValueFactory<>("voyage"));

        TableColumn<Expense, Float> impotColumn = new TableColumn<>("Impot");
        impotColumn.setCellValueFactory(new PropertyValueFactory<>("impot"));

        TableColumn<Expense, Float> autresColumn = new TableColumn<>("Autres");
        autresColumn.setCellValueFactory(new PropertyValueFactory<>("autres"));

        table.getColumns().clear();
        table.getColumns().addAll(periodeColumn, totalColumn, logementColumn, nourritureColumn, sortiesColumn, transportColumn, voyageColumn, impotColumn, autresColumn);

        items.addAll(expenses);
        table.setItems(FXCollections.observableArrayList(expenses));
    }
}
