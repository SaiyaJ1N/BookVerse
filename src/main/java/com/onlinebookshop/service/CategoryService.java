package com.onlinebookshop.service;

import com.onlinebookshop.dto.category.CategoryRequestDto;
import com.onlinebookshop.dto.category.CategoryResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponseDto save(CategoryRequestDto requestDto);

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto findById(Long id);

    CategoryResponseDto update(Long id, CategoryRequestDto requestDto);

    void deleteById(Long id);

}
