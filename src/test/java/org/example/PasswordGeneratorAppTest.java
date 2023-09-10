package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorAppTest {

    @Test
    void testGeneratePasswordWithLowercaseLetters() {
        PasswordGeneratorApp generator = new PasswordGeneratorApp();
        String password = generator.generatePassword(10, true, false, false, false);
        assertNotNull(password);
        assertEquals(10, password.length());
        assertTrue(password.matches("[a-z]+"), "Contains only lowercase letters");
    }

    @Test
    void testGeneratePasswordWithUppercaseLetters() {
        PasswordGeneratorApp generator = new PasswordGeneratorApp();
        String password = generator.generatePassword(12, false, true, false, false);
        assertNotNull(password);
        assertEquals(12, password.length());
        assertTrue(password.matches("[A-Z]+"), "Contains only uppercase letters");
    }
    @Test
    void testGeneratePasswordWithDigits() {
        PasswordGeneratorApp generator = new PasswordGeneratorApp();
        String password = generator.generatePassword(8, false, false, true, false);
        assertNotNull(password);
        assertEquals(8, password.length());
        assertTrue(password.matches("[0-9]+"), "Contains only digits");
    }

    @Test
    void testGeneratePasswordWithSpecialCharacters() {
        PasswordGeneratorApp generator = new PasswordGeneratorApp();
        String password = generator.generatePassword(15, false, false, false, true);
        assertNotNull(password);
        assertEquals(15, password.length());
        assertTrue(password.matches("[!@#$%^&*()_+\\-\\[\\]{}|;:<>,.?]+"), "Contains only special characters");
    }
    @Test
    void testGeneratePasswordLength() {
        PasswordGeneratorApp generator = new PasswordGeneratorApp();
        String password1 = generator.generatePassword(8, true, false, false, false);
        assertEquals(8, password1.length());

        String password2 = generator.generatePassword(12, false, true, true, false);
        assertEquals(12, password2.length());

        String password3 = generator.generatePassword(20, true, true, true, true);
        assertEquals(20, password3.length());
    }
    @Test
    void testGeneratePasswordWithEmptyLength() {
        PasswordGeneratorApp generator = new PasswordGeneratorApp();
        String password = generator.generatePassword(0, true, false, false, false);
        assertEquals("", password); // Oczekujemy pustego hasła
    }
    @Test
    void testGeneratePasswordWithAllOptionsDisabled() {
        PasswordGeneratorApp generator = new PasswordGeneratorApp();
        String password = generator.generatePassword(10, false, false, false, false);
        assertEquals("", password); // Oczekujemy pustego hasła
    }
    @Test
    void testGenerateRandomness() {
        PasswordGeneratorApp generator = new PasswordGeneratorApp();
        int numberOfPasswords = 100;
        Set<String> passwords = new HashSet<>();

        for (int i = 0; i < numberOfPasswords; i++) {
            String password = generator.generatePassword(10, true, true, true, true);
            passwords.add(password);
        }

        assertEquals(numberOfPasswords, passwords.size()); // Upewniamy się, że wszystkie hasła są różne
    }
}