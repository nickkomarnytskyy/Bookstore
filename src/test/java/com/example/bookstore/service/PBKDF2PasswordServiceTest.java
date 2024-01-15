package com.example.bookstore.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PBKDF2PasswordServiceTest {

    private PBKDF2PasswordService passwordService = new PBKDF2PasswordService();

    @Test
    void testSaltGeneration() {
        byte[] salt1 = passwordService.generateSalt();
        byte[] salt2 = passwordService.generateSalt();

        assertFalse(Arrays.equals(salt1, salt2)); // Ensure salts are different
        assertEquals(16, salt1.length);  // Ensure salt length is correct
    }

    @Test
    void testHash() {
        String password = "testpassword";
        byte[] salt = passwordService.generateSalt();
        byte[] hashedPassword1 = passwordService.hash(password, salt);
        byte[] hashedPassword2 = passwordService.hash(password, salt);

        assertArrayEquals(hashedPassword1, hashedPassword2); // Same password, same salt should produce same hash

        // Different password or salt should produce different hashes
        byte[] hashedPassword3 = passwordService.hash("differentpassword", salt);
        byte[] hashedPassword4 = passwordService.hash(password, passwordService.generateSalt());
        assertFalse(Arrays.equals(hashedPassword1, hashedPassword3));
        assertFalse(Arrays.equals(hashedPassword1, hashedPassword4));
    }

    @Test
    void testIsPasswordCorrect() {
        String password = "testpassword";
        byte[] salt = passwordService.generateSalt();
        byte[] hashedPassword = passwordService.hash(password, salt);

        assertTrue(passwordService.isPasswordCorrect(password, hashedPassword, salt));
        assertFalse(passwordService.isPasswordCorrect("wrongpassword", hashedPassword, salt));
    }

    @Test
    void testNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> passwordService.hash(null, new byte[16]));
        assertThrows(IllegalArgumentException.class, () -> passwordService.hash("password", null));
        assertThrows(IllegalArgumentException.class, () -> passwordService.isPasswordCorrect(null, new byte[16], new byte[16]));
        assertThrows(IllegalArgumentException.class, () -> passwordService.isPasswordCorrect("password", null, new byte[16]));
        assertThrows(IllegalArgumentException.class, () -> passwordService.isPasswordCorrect("password", new byte[16], null));
    }
}