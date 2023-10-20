package com.onlinebookshop.service.impl;

import com.onlinebookshop.dto.book.BookDto;
import com.onlinebookshop.dto.book.BookDtoWithoutCategoryIds;
import com.onlinebookshop.dto.book.BookSearchParameters;
import com.onlinebookshop.dto.book.CreateBookRequestDto;
import com.onlinebookshop.exception.EntityNotFoundException;
import com.onlinebookshop.mapper.BookMapper;
import com.onlinebookshop.model.Book;
import com.onlinebookshop.repository.book.BookRepository;
import com.onlinebookshop.repository.book.BookSpecificationBuilder;
import com.onlinebookshop.service.BookService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book bookModel = bookMapper.toModel(requestDto);
        bookModel.setCategories(bookMapper.toCategorySet(requestDto.getCategoriesId()));
        return bookMapper.toDto(bookRepository.save(bookModel));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        Optional<Book> bookById = bookRepository.findById(id);
        return bookById.map(bookMapper::toDto).orElseThrow(() ->
                new EntityNotFoundException("Can`t find book with id: " + id));
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can`t find book by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto params) {
        Book updatedBook = bookMapper.toModel(params);
        updatedBook.setId(id);
        return bookMapper.toDto(bookRepository.save(updatedBook));
    }

    @Override
    public List<BookDto> search(BookSearchParameters params) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);
        return bookRepository.findAll(bookSpecification).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long id) {
        return bookRepository.findAllByCategoriesId(id).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .collect(Collectors.toList());
    }
}
