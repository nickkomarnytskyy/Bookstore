package com.example.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookDTO {

    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private BigDecimal price;

    @NotBlank
    private Short year;
}
