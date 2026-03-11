package com.aagam.product.mapper;

import com.aagam.product.dto.CategoryRequestDto;
import com.aagam.product.dto.CategoryResponseDto;
import com.aagam.product.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    // Entity → Response DTO
    public static CategoryResponseDto toResponseDto(Category category) {
        if (category == null) return null;
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static List<CategoryResponseDto> toResponseDtoList(List<Category> categories) {
        if (categories == null) return List.of();
        return categories.stream().map(CategoryMapper::toResponseDto).collect(Collectors.toList());
    }

    // Request DTO → Entity
    public static Category toEntity(CategoryRequestDto dto) {
        if (dto == null) return null;
        return Category.builder()
                .name(dto.name())
                .build();
    }

    public static List<Category> toEntityList(List<CategoryRequestDto> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream().map(CategoryMapper::toEntity).collect(Collectors.toList());
    }
}