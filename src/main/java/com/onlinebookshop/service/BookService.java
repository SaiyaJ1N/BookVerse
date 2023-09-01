package com.onlinebookshop.service;

import com.onlinebookshop.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
