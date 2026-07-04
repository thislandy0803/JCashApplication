package org.example.main;

import org.example.service.Wallet;
import org.example.util.Database;
import org.example.util.BcryptPin;
import java.util.Scanner;

public class Main {

    static void main() {
        Scanner scanner = new Scanner(System.in);
        Wallet wallet = new Wallet();

        while (true) {
            System.out.println("\n----- Welcome to JCash -----");
            System.out.println("1. Login");
            System.out.println("2. Register New Account");
            System.out.println("3. Exit");
            System.out.print("Please Select an Option: ");
            String initialChoice = scanner.nextLine();


            switch (initialChoice) {
                case "1" -> {
                    System.out.println("\n" + "-----JCash Login-----");

                    int attempts = 0;
                    boolean isAuthenticated = false;

                    while (attempts < 3 && !isAuthenticated) {
                        System.out.print("Mobile Number: ");
                        String mobile = scanner.nextLine();

                        if (!mobile.matches("\\d{11}")) {
                            System.out.println("Please Try Again! Mobile number must be exactly 11 digits (e.g., 0987654321).");
                            System.out.println("Attempts left: " + (3 - ++attempts) + "\n");
                            continue;
                        }

                        if (Database.getUserByMobile(mobile) == null) {
                            System.out.println("The number you try to login is not registered to JCash.");
                            System.out.println("Attempts left: " + (3 - ++attempts) + "\n");
                            continue;
                        }

                        System.out.print("PIN: ");
                        String pin = scanner.nextLine();

                        if (!pin.matches("\\d{4}")) {
                            System.out.println("Please Try Again! PIN must be exactly a 4-digit number.");
                            System.out.println("Attempts left: " + (3 - ++attempts) + "\n");
                            continue;
                        }

                        if (wallet.login(mobile, pin)) {
                            isAuthenticated = true;
                            System.out.println("\nWelcome to JCash " + wallet.getLoggedInUser().getFullName());
                        } else {
                            System.out.println("Incorrect PIN. Please try again.");
                            System.out.println("Attempts left: " + (3 - ++attempts) + "\n");
                        }
                    }

                    if (!isAuthenticated) {
                        System.out.println("Please try again after 1 min due to 3 failed attempts. Exiting...");
                        scanner.close();
                        System.exit(0);
                    }


                    boolean running = true;
                    while (running) {
                        System.out.println("\n-----JCASH is the new GCASH-----");
                        System.out.println("1. Check Balance");
                        System.out.println("2. Cash-In");
                        System.out.println("3. Transfer Money");
                        System.out.println("4. Transaction Logs");
                        System.out.println("5. Logout");
                        System.out.println("6. Exit");
                        System.out.print("Please Select Your Option: ");

                        String choice = scanner.nextLine();

                        switch (choice) {
                            case "1":
                                System.out.printf("\nCurrent Balance: P%,.2f\n", wallet.getLoggedInUser().getBalance());
                                break;

                            case "2":
                                System.out.println("\nEnter Amount to Deposit");
                                System.out.print("Cash-In Amount: ");
                                double cashInAmount = Double.parseDouble(scanner.nextLine());
                                if (wallet.cashIn(cashInAmount)) {
                                    System.out.print("\nCash-In successful!");
                                    System.out.printf("\nYour New Balance is: P%,.2f\n", wallet.getLoggedInUser().getBalance());
                                }
                                break;

                            case "3":
                                System.out.print("\n-----Transfer Money-----");
                                System.out.print("\nSent to: ");
                                String receiverMobile = scanner.nextLine();
                                System.out.print("Amount: ");
                                double transferAmount = Double.parseDouble(scanner.nextLine());
                                if (wallet.transfer(receiverMobile, transferAmount)) {
                                    System.out.print("\nTransfer successful!");
                                    System.out.printf("\nYour New Balance is: P%,.2f\n", wallet.getLoggedInUser().getBalance());
                                }
                                break;

                            case "4":
                                wallet.printLogs();
                                break;

                            case "5":
                                System.out.println("\nLogout!");
                                wallet.logout();
                                running = false;
                                break;

                            case "6":
                                System.out.println("\nThank you for using JCash!");
                                wallet.logout();
                                scanner.close();
                                System.exit(0);

                            default:
                                System.out.println("Please try again.");
                        }
                    }
                }
                case "2" -> {
                    System.out.println("\n-----JCash Account Registration-----");

                    System.out.print("Enter Full Name: ");
                    String fullName = scanner.nextLine();
                    if (fullName.trim().isEmpty()) {
                        System.out.println("Registration Failed! Name cannot be empty.");
                        continue;
                    }

                    System.out.print("Enter Mobile Number (11 digits): ");
                    String mobile = scanner.nextLine();
                    if (!mobile.matches("\\d{11}")) {
                        System.out.println("Registration Failed! Mobile number must be exactly 11 digits.");
                        continue;
                    }

                    if (Database.getUserByMobile(mobile) != null) {
                        System.out.println("Registration Failed! This mobile number is already registered.");
                        continue;
                    }

                    System.out.print("Create a 4-Digit PIN: ");
                    String pin = scanner.nextLine();
                    if (!pin.matches("\\d{4}")) {
                        System.out.println("Registration Failed! PIN must be exactly a 4-digit number.");
                        continue;
                    }

                    String encryptedPin = BcryptPin.hashPin(pin);

                    if (Database.registerUser(fullName, mobile, encryptedPin)) {
                        System.out.println("\nRegistration Successful! You can now log in with your account.");
                    } else {
                        System.out.println("\nAn error occurred during database registration. Please try again.");
                    }
                }
                case "3" -> {
                    System.out.println("\nThank you for using JCash!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Please try again");
            }
        }
    }
}