package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "publicationYear", target = "year")
    BookDTO bookToBookDto(Book book);

    @Mapping(source = "year", target = "publicationYear")
    Book bookDTOToBook(BookDTO book);
}
