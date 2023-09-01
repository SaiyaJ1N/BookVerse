package com.onlinebookshop;

import com.onlinebookshop.model.Book;
import com.onlinebookshop.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookShopApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book seaWolfBook = new Book();
            seaWolfBook.setPrice(BigDecimal.valueOf(200));
            seaWolfBook.setAuthor("Jack London");
            seaWolfBook.setTitle("Sea Wolf");
            seaWolfBook.setIsbn("w346efydfhe000w");

            bookService.save(seaWolfBook);
            System.out.println(bookService.findAll());
        };
    }
}
