package com.example.demo.db;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    /**
     * Location of database
     */
    private static final String location = "database.db";

    public static boolean isOK() {
        return checkDrivers() && checkConnection() && createTablesIfNotExists();
    }

    private static boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new JDBC());
            return true;
        } catch (ClassNotFoundException | SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not start SQLite Drivers", exception);
            return false;
        }
    }

    private static boolean checkConnection() {
        try (Connection connection = connect()) {
            return connection != null;
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not connect to database", e);
            return false;
        }
    }

    private static boolean createTablesIfNotExists() {
        String createExpenseTable = """
                CREATE TABLE IF NOT EXISTS expense(
                     periode TEXT NOT NULL,
                     logement REAL NOT NULL,
                     nourriture REAL NOT NULL,
                     sorties REAL NOT NULL,
                     transport REAL NOT NULL,
                     voyage REAL NOT NULL,
                     impot REAL NOT NULL,
                     autres REAL NOT NULL
                );
                """;

        String createIncomeTable = """
                CREATE TABLE IF NOT EXISTS income(
                     periode TEXT NOT NULL,
                     salaire REAL NOT NULL,
                     aides REAL NOT NULL,
                     autoEntreprise REAL NOT NULL,
                     revenusPassifs REAL NOT NULL,
                     autres REAL NOT NULL
                );
                """;

        try (Connection connection = Database.connect()) {
            PreparedStatement statementExpense = connection.prepareStatement(createExpenseTable);
            statementExpense.executeUpdate();

            PreparedStatement statementIncome = connection.prepareStatement(createIncomeTable);
            statementIncome.executeUpdate();

            return true;
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not create or find tables in database", exception);
            return false;
        }
    }

    protected static Connection connect() {
        String dbPrefix = "jdbc:sqlite:";
        try {
            return DriverManager.getConnection(dbPrefix + location);
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    LocalDateTime.now() + ": Could not connect to SQLite DB at " +
                            location, exception);
            return null;
        }
    }
}
