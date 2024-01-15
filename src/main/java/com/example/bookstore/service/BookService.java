package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBook(String id);

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(BookDTO book, String id);

    void deleteBookById(String id);

}
