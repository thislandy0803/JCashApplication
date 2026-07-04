package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptPin {

    public static String hashPin(String pin) {
        return BCrypt.hashpw(pin, BCrypt.gensalt(12));
    }

    public static boolean checkPin(String plainPin, String hashedPin) {
        try {
            return BCrypt.checkpw(plainPin, hashedPin);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Error in Verifying PIN encryption" + e.getMessage());
            return false;
        }
    }
}
