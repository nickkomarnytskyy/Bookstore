package com.example.bookstore.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import static org.springframework.util.Assert.notNull;

@Slf4j
@Component
public class PBKDF2PasswordService implements PasswordService {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128;   // Adjust as needed for security
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    @Override
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] hash(String password, byte[] salt) {
        notNull(password, "Password must not be null");
        notNull(salt, "Salt must not be null");

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Error hashing password: {}", e.getMessage());
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @Override
    public boolean isPasswordCorrect(String password, byte[] hash, byte[] salt) {
        notNull(password, "Password must not be null");
        notNull(salt, "Salt must not be null");
        notNull(hash, "Hash must not be null");

        byte[] hashedAttemptedPassword = hash(password, salt);
        return Arrays.equals(hashedAttemptedPassword, hash);
    }

}
