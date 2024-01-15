package com.example.bookstore.service;

import com.example.bookstore.dto.request.RegistrationRequest;
import com.example.bookstore.entity.UserActivity;
import com.example.bookstore.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class MongoServiceImpl implements MongoService {

    private final UserActivityRepository userActivityRepository;

    @Override
    public void logRegistration(RegistrationRequest registrationRequest) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUserName(registrationRequest.getUsername());
        userActivity.setTimestamp(new Date());
        userActivity.setActivityType(UserActivity.ActivityType.REGISTRATION);

        userActivityRepository.save(userActivity);
        log.info("Saved user activity log: {}", userActivity);
    }
}
