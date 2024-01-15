package com.example.bookstore.mapper;

import com.example.bookstore.dto.response.UserResponse;
import com.example.bookstore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserDto(User user);

}
