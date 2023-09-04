package com.onlinebookshop.service.impl;

import com.onlinebookshop.dto.BookDto;
import com.onlinebookshop.dto.CreateBookRequestDto;
import com.onlinebookshop.exception.EntityNotFoundException;
import com.onlinebookshop.mapper.BookMapper;
import com.onlinebookshop.model.Book;
import com.onlinebookshop.repository.BookRepository;
import com.onlinebookshop.service.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book bookModel = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(bookModel));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findByBookId(Long id) {
        Optional<Book> bookById = bookRepository.findBookById(id);
        return bookById.map(bookMapper::toDto).orElseThrow(() ->
                new EntityNotFoundException("Can`t find book with id: " + id));
    }
}
