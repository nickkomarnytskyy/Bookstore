package com.example.bookstore.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
@ToString
public class UserActivity {

    @Id
    public String id;
    private String userName;
    private Date timestamp;
    private ActivityType activityType;

    public enum ActivityType {
        REGISTRATION, LOGIN
    }
}
