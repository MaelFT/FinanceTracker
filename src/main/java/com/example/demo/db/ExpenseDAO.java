package com.example.demo.db;

import com.example.demo.controller.AddLineController;
import com.example.demo.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpenseDAO {

    public ExpenseDAO() {}

    public void save(Expense expense) {
        String query = "INSERT INTO expense (periode, logement, nourriture, sorties, transport, voyage, impot, autres) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, expense.getPeriode());
            statement.setDouble(2, expense.getLogement());
            statement.setDouble(3, expense.getNourriture());
            statement.setDouble(4, expense.getSorties());
            statement.setDouble(5, expense.getTransport());
            statement.setDouble(6, expense.getVoyage());
            statement.setDouble(7, expense.getImpot());
            statement.setDouble(8, expense.getAutres());

            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not insert expense into database", exception);
        }
    }

    public static List<Expense> allExpenses() {
        String query = "SELECT * FROM expense";

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            List<Expense> expenseList = new ArrayList<>();

            while (resultSet.next()) {
                String periode = resultSet.getString("periode");
                Float logement = resultSet.getFloat("logement");
                Float nourriture = resultSet.getFloat("nourriture");
                Float sorties = resultSet.getFloat("sorties");
                Float transport = resultSet.getFloat("transport");
                Float voyage = resultSet.getFloat("voyage");
                Float impot = resultSet.getFloat("impot");
                Float autres = resultSet.getFloat("autres");

                Float total = logement + nourriture + sorties + transport + voyage + impot + autres;

                Expense expense = new Expense(periode, total, logement, nourriture, sorties, transport, voyage, impot, autres);
                expenseList.add(expense);
            }

            return expenseList;

        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not retrieve expenses from database", exception);
        }
        return null;
    }



}
