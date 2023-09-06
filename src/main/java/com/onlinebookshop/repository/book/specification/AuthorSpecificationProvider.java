package com.onlinebookshop.repository.book.specification;

import com.onlinebookshop.model.Book;
import com.onlinebookshop.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "author";
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get("book").in(Arrays.stream(params).toArray());
    }
}
