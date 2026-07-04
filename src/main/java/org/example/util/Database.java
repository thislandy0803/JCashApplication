package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.model.Transaction;
import org.example.model.User;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/jcash_romol";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }



    public static boolean registerUser(String fullName, String mobileNumber, String encryptedPin) {
        String query = "INSERT INTO users (full_name, mobile_number, pin, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fullName);
            stmt.setString(2, mobileNumber);
            stmt.setString(3, encryptedPin);
            stmt.setDouble(4, 0.00); // New accounts setup with P0.00 balance

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saving new registration to database: " + e.getMessage());
            return false;
        }
    }


    public static User getUserByMobile(String mobile) {
        String query = "SELECT * FROM users WHERE mobile_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, mobile);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("mobile_number"),
                            rs.getString("pin"),
                            rs.getString("full_name"),
                            rs.getDouble("balance")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error from database: " + e.getMessage());
        }
        return null;
    }


    public static boolean updateUserBalance(String mobile, double newBalance) {
        String query = "UPDATE users SET balance = ? WHERE mobile_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, newBalance);
            stmt.setString(2, mobile);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating balance in database: " + e.getMessage());
            return false;
        }
    }



    public static void saveTransaction(String mobile, String type, double amount, String details) {
        String query = "INSERT INTO transactions_logs (user_mobile, type, amount, details) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mobile);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            stmt.setString(4, details);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in saving Transaction logs: " + e.getMessage());
        }
    }


    public static List<Transaction> getTransactionsByMobile(String mobile) {
        List<Transaction> list = new ArrayList<>();
        String query = "SELECT * FROM transactions_logs WHERE user_mobile = ? ORDER BY date_time DESC";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mobile);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Transaction(
                            rs.getString("type"),
                            rs.getDouble("amount"),
                            rs.getString("details"),
                            rs.getTimestamp("date_time").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in Transaction logs Database: " + e.getMessage());
        }
        return list;
    }
}