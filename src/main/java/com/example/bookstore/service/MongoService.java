package com.example.bookstore.service;

import com.example.bookstore.dto.request.RegistrationRequest;

public interface MongoService {
    void logRegistration(RegistrationRequest registrationRequest);
}
