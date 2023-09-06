package com.onlinebookshop.repository;

import com.onlinebookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    String getKey();

   Specification<T> getSpecification(String[] params);
}
