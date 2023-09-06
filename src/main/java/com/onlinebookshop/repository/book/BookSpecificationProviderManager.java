package com.onlinebookshop.repository.book;

import com.onlinebookshop.model.Book;
import com.onlinebookshop.repository.SpecificationProvider;
import com.onlinebookshop.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(b -> b.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                       new RuntimeException("Can`t find specification provider for the key: "
                               + key));
    }
}
