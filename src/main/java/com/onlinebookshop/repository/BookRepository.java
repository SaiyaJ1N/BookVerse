package com.onlinebookshop.repository;

import com.onlinebookshop.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookById(Long id);

}
