package com.example.bookstore.service;

import com.example.bookstore.dto.request.RegistrationRequest;
import com.example.bookstore.dto.response.UserResponse;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private PasswordService passwordService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MongoService mongoService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<RegistrationRequest> registrationDTOArgumentCaptor;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testRegisterUser_success() {
        // Arrange
        byte[] generatedSalt = new byte[]{1, 2, 3}; // Example salt
        byte[] hash = new byte[]{4, 5, 6}; // Example hash
        RegistrationRequest registrationRequest = new RegistrationRequest("testuser", "password");

        User savedUser = new User();
        savedUser.setId("someId");
        savedUser.setUserName(registrationRequest.getUsername());
        savedUser.setPassword(hash);
        savedUser.setSalt(generatedSalt);

        UserResponse expectedResponse = new UserResponse("someId", "testuser");

        when(passwordService.generateSalt()).thenReturn(generatedSalt);
        when(passwordService.hash(any(), any())).thenReturn(hash);
        when(userRepository.save(any())).thenReturn(savedUser);

        // Act
        UserResponse response = userService.registerUser(registrationRequest);

        // Assert
        assertEquals(expectedResponse, response);
        verify(passwordService).generateSalt();
        verify(passwordService).hash(registrationRequest.getPassword(), generatedSalt);
        verify(userRepository).save(userArgumentCaptor.capture());
        verify(mongoService).logRegistration(registrationDTOArgumentCaptor.capture());

        User userArgumentCaptorValue = userArgumentCaptor.getValue();
        assertNotNull(userArgumentCaptorValue);
        assertEquals(registrationRequest.getUsername(), userArgumentCaptorValue.getUserName());
        assertEquals(hash, userArgumentCaptorValue.getPassword());
        assertEquals(generatedSalt, userArgumentCaptorValue.getSalt());

        RegistrationRequest registrationRequestArgumentCaptorValue = registrationDTOArgumentCaptor.getValue();
        assertNotNull(registrationRequestArgumentCaptorValue);
        assertEquals(registrationRequest, registrationRequestArgumentCaptorValue);
    }
}