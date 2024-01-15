package com.example.bookstore.controller;

import com.example.bookstore.dto.request.RegistrationRequest;
import com.example.bookstore.dto.response.UserResponse;
import com.example.bookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest);
    }

}
