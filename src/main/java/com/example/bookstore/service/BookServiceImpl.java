package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper.INSTANCE::bookToBookDto).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBook(String id) {
        return bookRepository.findById(id).map(BookMapper.INSTANCE::bookToBookDto).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        var savedEntity = bookRepository.save(BookMapper.INSTANCE.bookDTOToBook(bookDTO));
        return BookMapper.INSTANCE.bookToBookDto(savedEntity);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, String id) {
        return bookRepository.findById(id)
                .map(bookEntity -> {
                    bookEntity.setTitle(bookDTO.getTitle());
                    bookEntity.setAuthor(bookDTO.getAuthor());
                    bookEntity.setPrice(bookDTO.getPrice());
                    bookEntity.setPublicationYear(bookDTO.getYear());
                    return BookMapper.INSTANCE.bookToBookDto(bookRepository.save(bookEntity));
                })
                .orElseGet(() -> {
                    Book savedEntity = bookRepository.save(BookMapper.INSTANCE.bookDTOToBook(bookDTO));
                    return BookMapper.INSTANCE.bookToBookDto(savedEntity);
                });
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
    }
}
