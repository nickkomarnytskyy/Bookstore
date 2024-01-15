package com.example.bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RegistrationRequest {

    @NotBlank(message = "The username is required.")
    private final String username;

    @NotBlank(message = "The password is required.")
    private final String password;

}
