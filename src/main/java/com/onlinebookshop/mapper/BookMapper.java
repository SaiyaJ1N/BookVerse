package com.onlinebookshop.mapper;

import com.onlinebookshop.config.MapperConfig;
import com.onlinebookshop.dto.book.BookDto;
import com.onlinebookshop.dto.book.CreateBookRequestDto;
import com.onlinebookshop.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
