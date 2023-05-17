package com.archers.passwordmanager;

import java.util.Random;

public class PasswordGenerator {
    private int length;
    private boolean upperCase;
    private boolean lowerCase ;
    private boolean includeNumbers;
    private boolean includeSymbols;

    public PasswordGenerator(int length, boolean upperCase, boolean lowerCase, boolean includeNumbers, boolean includeSymbols) {
        this.length = length;
        this.upperCase = upperCase;
        this.lowerCase = lowerCase;
        this.includeNumbers = includeNumbers;
        this.includeSymbols = includeSymbols;
    }

    public String generatePassword() {
        String characters = "";

        if (upperCase) {
            characters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if (lowerCase) {
            characters += "abcdefghijklmnopqrstuvwxyz";
        }
        if (includeNumbers) {
            characters += "0123456789";
        }
        if (includeSymbols) {
            characters += "!@#$%^&*()-_=+[]{};:,.<>/?";
        }

        if (characters.isEmpty()) {
            return "Invalid password configuration.";
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}

