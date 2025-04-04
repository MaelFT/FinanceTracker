package com.example.demo.controller;

import com.example.demo.db.ExpenseDAO;
import com.example.demo.model.Expense;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;

import java.util.List;

public class FinanceController {

    @FXML private PieChart pieChart;
    @FXML private LineChart<String, Number> lineChart;
    @FXML private ComboBox<String> dateSelector;

    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    private List<Expense> expenses;

    @FXML
    public void initialize() {
        refreshData();

        xAxis.setLabel("Période");
        xAxis.getCategories().clear();

        for (Expense expense : expenses) {
            if (!xAxis.getCategories().contains(expense.getPeriode())) {
                xAxis.getCategories().add(expense.getPeriode());
            }
        }

        dateSelector.setOnAction(e -> {
            String selectedPeriod = dateSelector.getValue();
            for (Expense expense : expenses) {
                if (expense.getPeriode().equals(selectedPeriod)) {
                    updateCharts(expense);
                    break;
                }
            }
        });
    }

    private void refreshData() {
        expenses = ExpenseDAO.allExpenses();

        dateSelector.getItems().clear();
        for (Expense expense : expenses) {
            if (!dateSelector.getItems().contains(expense.getPeriode())) {
                dateSelector.getItems().add(expense.getPeriode());
            }
        }

        if (!expenses.isEmpty()) {
            Expense latest = expenses.get(expenses.size() - 1);
            dateSelector.setValue(latest.getPeriode());
            updateCharts(latest);
        }
    }

    private void updateCharts(Expense selectedExpense) {
        pieChart.getData().clear();
        lineChart.getData().clear();
        xAxis.getCategories().clear();

        pieChart.getData().addAll(
                new PieChart.Data("Logement", selectedExpense.getLogement()),
                new PieChart.Data("Nourriture", selectedExpense.getNourriture()),
                new PieChart.Data("Sorties", selectedExpense.getSorties()),
                new PieChart.Data("Transport", selectedExpense.getTransport()),
                new PieChart.Data("Voyage", selectedExpense.getVoyage()),
                new PieChart.Data("Impôts", selectedExpense.getImpot()),
                new PieChart.Data("Autres", selectedExpense.getAutres())
        );

        XYChart.Series<String, Number> logementSeries = new XYChart.Series<>();
        logementSeries.setName("Logement");

        XYChart.Series<String, Number> nourritureSeries = new XYChart.Series<>();
        nourritureSeries.setName("Nourriture");

        XYChart.Series<String, Number> sortiesSeries = new XYChart.Series<>();
        sortiesSeries.setName("Sorties");

        XYChart.Series<String, Number> transportSeries = new XYChart.Series<>();
        transportSeries.setName("Transport");

        XYChart.Series<String, Number> voyageSeries = new XYChart.Series<>();
        voyageSeries.setName("Voyage");

        XYChart.Series<String, Number> impotSeries = new XYChart.Series<>();
        impotSeries.setName("Impôts");

        XYChart.Series<String, Number> autresSeries = new XYChart.Series<>();
        autresSeries.setName("Autres");

        for (Expense expense : expenses) {
            String periode = expense.getPeriode();
            xAxis.getCategories().add(periode);

            logementSeries.getData().add(new XYChart.Data<>(periode, expense.getLogement()));
            nourritureSeries.getData().add(new XYChart.Data<>(periode, expense.getNourriture()));
            sortiesSeries.getData().add(new XYChart.Data<>(periode, expense.getSorties()));
            transportSeries.getData().add(new XYChart.Data<>(periode, expense.getTransport()));
            voyageSeries.getData().add(new XYChart.Data<>(periode, expense.getVoyage()));
            impotSeries.getData().add(new XYChart.Data<>(periode, expense.getImpot()));
            autresSeries.getData().add(new XYChart.Data<>(periode, expense.getAutres()));
        }

        lineChart.getData().addAll(
                logementSeries,
                nourritureSeries,
                sortiesSeries,
                transportSeries,
                voyageSeries,
                impotSeries,
                autresSeries
        );
    }
}
