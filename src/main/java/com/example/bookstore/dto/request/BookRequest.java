package com.example.bookstore.dto.request;


import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class BookRequest {

    private String title;

    private String author;

    private BigDecimal price;

    private Short publicationYear;

}
