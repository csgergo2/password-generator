package csg.passwordgenerator;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PasswordGenerator {
    private static final int MINIMUM_CHARACTERS = 8;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        printoutPassword(true);
        printoutPassword(false);
    }

    private static void printoutPassword(boolean includeSpecialChars) {
        System.out.println("includeSpecialChars: " + includeSpecialChars);
        System.out.println("length  8: " + generatePassword(8, includeSpecialChars));
        System.out.println("length 16: " + generatePassword(16, includeSpecialChars));
        System.out.println("length 24: " + generatePassword(24, includeSpecialChars));
        System.out.println("length 32: " + generatePassword(32, includeSpecialChars));
        System.out.println("length 40: " + generatePassword(40, includeSpecialChars));
        System.out.println("length 48: " + generatePassword(48, includeSpecialChars));
        System.out.println("length 64: " + generatePassword(64, includeSpecialChars));
        System.out.println("length 100: " + generatePassword(100, includeSpecialChars));
    }

    private static String generatePassword(int length, boolean includeSpecialChars) {
        if (length < MINIMUM_CHARACTERS) {
            throw new IllegalArgumentException("Password length must be at least " + MINIMUM_CHARACTERS + " characters.");
        }

        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialChars = includeSpecialChars ? "+!%?*@&#<>_$ßŁ" : "";

        StringBuilder password = new StringBuilder(length);
        while (password.length() < length) {
            if (!specialChars.isEmpty()) {
                char nextChar;
                do {
                    nextChar = specialChars.charAt(RANDOM.nextInt(specialChars.length()));
                } while (password.toString().indexOf(nextChar) != -1);
                password.append(nextChar);
                specialChars = specialChars.replace(String.valueOf(nextChar), "");
            }
            password.append(digits.charAt(RANDOM.nextInt(digits.length())));
            password.append(upperCase.charAt(RANDOM.nextInt(digits.length())));
            password.append(lowerCase.charAt(RANDOM.nextInt(digits.length())));
        }

        List<Character> passwordChars = password.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        Collections.shuffle(passwordChars);

        return passwordChars.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
