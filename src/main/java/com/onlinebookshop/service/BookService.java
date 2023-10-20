package com.onlinebookshop.service;

import com.onlinebookshop.dto.book.BookDto;
import com.onlinebookshop.dto.book.BookDtoWithoutCategoryIds;
import com.onlinebookshop.dto.book.BookSearchParameters;
import com.onlinebookshop.dto.book.CreateBookRequestDto;
import com.onlinebookshop.model.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    Book findBookById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, CreateBookRequestDto params);

    List<BookDto> search(BookSearchParameters params);

    List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long id);
}
