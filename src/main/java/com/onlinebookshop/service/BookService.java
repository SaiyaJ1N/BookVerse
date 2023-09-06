package com.onlinebookshop.service;

import com.onlinebookshop.dto.BookDto;
import com.onlinebookshop.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void deleteById(Long id);
}
