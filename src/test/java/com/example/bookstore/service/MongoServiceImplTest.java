package com.example.bookstore.service;

import com.example.bookstore.dto.request.RegistrationRequest;
import com.example.bookstore.entity.UserActivity;
import com.example.bookstore.repository.UserActivityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MongoServiceImplTest {

    @Mock
    private UserActivityRepository userActivityRepository;

    @Captor
    private ArgumentCaptor<UserActivity> userActivityArgumentCaptor;

    @InjectMocks
    private MongoServiceImpl mongoServiceImpl;

    @Test
    void testLogRegistration() {
        // Set up test data
        RegistrationRequest registrationRequest = new RegistrationRequest("usr", "pwd");

        // Call the method to be tested
        mongoServiceImpl.logRegistration(registrationRequest);

        // Verify interactions with the mock
        verify(userActivityRepository).save(userActivityArgumentCaptor.capture());

        UserActivity value = userActivityArgumentCaptor.getValue();

        assertEquals("usr", value.getUserName());
        assertEquals(UserActivity.ActivityType.REGISTRATION, value.getActivityType());
    }
}