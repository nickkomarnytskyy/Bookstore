package com.example.bookstore.service;

import com.example.bookstore.dto.request.RegistrationRequest;
import com.example.bookstore.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(RegistrationRequest registrationRequest);
}
