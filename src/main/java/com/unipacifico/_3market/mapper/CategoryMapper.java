package com.unipacifico._3market.mapper;

import com.unipacifico._3market.dto.CategoryDto;
import com.unipacifico._3market.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .productCount(category.getProducts().size())
                .build();
    }

    public List<CategoryDto> toDtoList(List<Category> categories) {
        return categories.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Category toEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
