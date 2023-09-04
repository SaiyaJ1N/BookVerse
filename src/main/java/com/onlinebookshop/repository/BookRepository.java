package com.onlinebookshop.repository;

import com.onlinebookshop.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
