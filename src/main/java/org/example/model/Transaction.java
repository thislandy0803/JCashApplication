package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Transaction(String type, double amount, String details, LocalDateTime dateTime) {

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a");
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime + " | " + type + " | P" + String.format("%,.2f", amount) + " | " + details;
    }
}