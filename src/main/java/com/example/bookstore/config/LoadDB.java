package com.example.bookstore.config;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class LoadDB {

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {

        return args -> {
//            log.info("Preloading " + repository.save(new Book()));
//            log.info("Preloading " + repository.save(new Book()));
        };
    }
}