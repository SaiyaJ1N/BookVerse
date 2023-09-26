package com.onlinebookshop.controller;

import com.onlinebookshop.dto.book.BookDto;
import com.onlinebookshop.dto.book.BookSearchParameters;
import com.onlinebookshop.dto.book.CreateBookRequestDto;
import com.onlinebookshop.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book Manager", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get all books", description = "Get a list of books")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @Operation(summary = "Get book", description = "Get book by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public BookDto getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @Operation(summary = "Delete book", description = "Delete book by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @Operation(summary = "Update book", description = "Updating book by id with new parameters")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto update(@PathVariable Long id, @RequestBody @Valid CreateBookRequestDto params) {
        return bookService.update(id, params);
    }

    @Operation(summary = "Create a new book", description = "Create a new book")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @Operation(summary = "Search books", description = "Searching books by parameters")
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<BookDto> search(BookSearchParameters searchParameters) {
        return bookService.search(searchParameters);
    }
}
