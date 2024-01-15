package com.example.bookstore.service;

public interface PasswordService {
    byte[] generateSalt();

    byte[] hash(String password, byte[] salt);

    boolean isPasswordCorrect(String password, byte[] hash, byte[] salt);

}
