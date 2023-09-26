package com.onlinebookshop.mapper;

import com.onlinebookshop.config.MapperConfig;
import com.onlinebookshop.dto.category.CategoryRequestDto;
import com.onlinebookshop.dto.category.CategoryResponseDto;
import com.onlinebookshop.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toModel(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toResponseDto(Category category);
}
