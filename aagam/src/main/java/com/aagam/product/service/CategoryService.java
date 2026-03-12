package com.aagam.product.service;

import com.aagam.product.dto.CategoryRequestDto;
import com.aagam.product.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    CategoryResponseDto createCategory(CategoryRequestDto dto);

    CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto);

    void deleteCategory(Long id);
}