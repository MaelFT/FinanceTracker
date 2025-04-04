package com.example.demo.db;

import com.example.demo.model.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncomeDAO {

    public IncomeDAO() {}

    public static double getTotalFromDate(String periode) {
        String query = "SELECT salaire, aides, autoEntreprise, revenusPassifs, autres FROM income WHERE periode = ?";
        double totalIncome = 0.0;

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, periode);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                double salaire = rs.getDouble("salaire");
                double aides = rs.getDouble("aides");
                double autoEntreprise = rs.getDouble("autoEntreprise");
                double revenusPassifs = rs.getDouble("revenusPassifs");
                double autres = rs.getDouble("autres");

                totalIncome += salaire + aides + autoEntreprise + revenusPassifs + autres;
            }

        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not retrieve income total from database", exception);
        }

        return totalIncome;
    }

    public void save(Income income) {
        String query = "INSERT INTO income (periode, salaire, aides, autoEntreprise, revenusPassifs, autres) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, income.getPeriode());
            statement.setDouble(2, income.getSalaire());
            statement.setDouble(3, income.getAides());
            statement.setDouble(4, income.getAutoEntreprise());
            statement.setDouble(5, income.getRevenusPassifs());
            statement.setDouble(6, income.getAutres());

            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not insert income into database", exception);
        }
    }

    public static List<Income> allIncomes() {
        String query = "SELECT * FROM income";

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            List<Income> incomeList = new ArrayList<>();

            while (resultSet.next()) {
                String periode = resultSet.getString("periode");
                Float salaire = resultSet.getFloat("salaire");
                Float aides = resultSet.getFloat("aides");
                Float autoEntreprise = resultSet.getFloat("autoEntreprise");
                Float revenusPassifs = resultSet.getFloat("revenusPassifs");
                Float autres = resultSet.getFloat("autres");

                Float total = salaire + aides + autoEntreprise + revenusPassifs + autres;

                Income income = new Income(periode, total, salaire, aides, autoEntreprise, revenusPassifs, autres);
                incomeList.add(income);
            }

            return incomeList;

        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not retrieve incomes from database", exception);
        }
        return null;
    }
}
