package com.aagam.product.service.impl;

import com.aagam.product.dto.CategoryRequestDto;
import com.aagam.product.dto.CategoryResponseDto;
import com.aagam.product.entity.Category;
import com.aagam.product.mapper.CategoryMapper;
import com.aagam.product.repository.CategoryRepository;
import com.aagam.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return CategoryMapper.toResponseDtoList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return CategoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        Category category = CategoryMapper.toEntity(dto);
        return CategoryMapper.toResponseDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        existing.setName(dto.name());
        return CategoryMapper.toResponseDto(existing);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }
}