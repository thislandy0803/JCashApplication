package org.example.service;

import org.example.model.Transaction;
import org.example.model.User;
import org.example.util.BcryptPin;
import org.example.util.Database;
import java.util.List;

public class Wallet {
    private User loggedInUser;

    public boolean login(String mobileNumber, String pin) {
        User user = Database.getUserByMobile(mobileNumber);
        if (user != null && BcryptPin.checkPin(pin, user.getPin())) {
            this.loggedInUser = user;
            return true;
        }
        return false;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        this.loggedInUser = null;
    }

    public boolean cashIn(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid Amount. Please try again!");
            return false;
        }

        double newBalance = loggedInUser.getBalance() + amount;

        if (Database.updateUserBalance(loggedInUser.getMobileNumber(), newBalance)) {
            loggedInUser.setBalance(newBalance);


            Database.saveTransaction(loggedInUser.getMobileNumber(), "CASH-IN", amount, "Deposit to your JCash Balance");
            return true;
        } else {
            System.out.println("Cash-In failed.");
            return false;
        }
    }

    public boolean transfer(String receiverMobile, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid Amount. Please try again!");
            return false;
        }
        if (loggedInUser.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return false;
        }

        User receiver = Database.getUserByMobile(receiverMobile);
        if (receiver == null) {
            System.out.println("This mobile number is not registered in JCash.");
            return false;
        }
        if (receiverMobile.equals(loggedInUser.getMobileNumber())) {
            System.out.println("You cannot transfer money to yourself.");
            return false;
        }

        double newSenderBalance = loggedInUser.getBalance() - amount;
        double newReceiverBalance = receiver.getBalance() + amount;

        if (Database.updateUserBalance(loggedInUser.getMobileNumber(), newSenderBalance) &&
                Database.updateUserBalance(receiver.getMobileNumber(), newReceiverBalance)) {

            loggedInUser.setBalance(newSenderBalance);


            Database.saveTransaction(loggedInUser.getMobileNumber(), "Transfer Out", amount, "Sent to Mr/Ms [" + receiver.getFullName() + "] With the mobile number of [" + receiverMobile + "]");
            Database.saveTransaction(receiverMobile, "Transfer In", amount, "Received from Mr/Ms [" + (loggedInUser.getFullName()) + "] With the mobile number of [" + loggedInUser.getMobileNumber() + "]");

            return true;
        } else {
            System.out.println("Transfer failed.");
            return false;
        }
    }

    public void printLogs() {
        System.out.println("\n----- Transaction Logs -----");
        System.out.println("\n|-------Date & Time------|--Transfer Type--|---Amount---|---Details---|");

        List<Transaction> dbHistory = Database.getTransactionsByMobile(loggedInUser.getMobileNumber());

        if (dbHistory.isEmpty()) {
            System.out.println("No transactions");
        } else {
            for (Transaction t : dbHistory) {
                System.out.println(t.toString());
            }
        }
    }
}