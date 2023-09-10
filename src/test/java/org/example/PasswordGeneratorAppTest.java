package org.example;

import org.junit.jupiter.api.Test;

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
}