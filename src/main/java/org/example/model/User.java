package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String mobileNumber;
    private String pin;
    private String fullName;
    private double balance;
    private List<Transaction> transactionList;


    public User() {
        this.transactionList = new ArrayList<>();
    }

    public User(long id, String mobileNumber, String pin, String fullName, double initialBalance) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.pin = pin;
        this.fullName = fullName;
        this.balance = initialBalance;
        this.transactionList = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public String getPin() {
        return pin;
    }


    public void setPin(String pin) {
        this.pin = pin;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", fullName='" + fullName + '\'' +
                ", balance=" + balance +
                ", transactionList=" + transactionList +
                '}';
    }
}
