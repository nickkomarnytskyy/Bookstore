package com.example.bookstore.service;

import com.example.bookstore.dto.request.RegistrationRequest;
import com.example.bookstore.dto.response.UserResponse;
import com.example.bookstore.entity.User;
import com.example.bookstore.mapper.UserMapper;
import com.example.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordService passwordService;
    private final UserRepository userRepository;
    private final MongoService mongoService;

    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {
        byte[] generatedSalt = passwordService.generateSalt();
        byte[] hash = passwordService.hash(registrationRequest.getPassword(), generatedSalt);

        User user = new User();
        user.setUserName(registrationRequest.getUsername());
        user.setPassword(hash);
        user.setSalt(generatedSalt);

        User savedEntity = userRepository.save(user);
        //should be done async
        mongoService.logRegistration(registrationRequest);

        return UserMapper.INSTANCE.userToUserDto(savedEntity);
    }
}
