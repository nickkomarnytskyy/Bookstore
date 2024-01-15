package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "price", columnDefinition = "Decimal(10,2)")
    private BigDecimal price;

    @Column(name = "publication_year")
    private Short publicationYear;
}
