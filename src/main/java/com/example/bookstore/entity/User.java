package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(columnDefinition = "BINARY(64)", nullable = false)
    private byte[] password;

    @Column(columnDefinition = "BINARY(64)", nullable = false)
    private byte[] salt;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Order> orders;

}

