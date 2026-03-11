package com.aagam.product.service.impl;

import com.aagam.product.dto.ProductRequestDto;
import com.aagam.product.dto.ProductResponseDto;
import com.aagam.product.entity.Category;
import com.aagam.product.entity.Product;
import com.aagam.product.mapper.ProductMapper;
import com.aagam.product.repository.CategoryRepository;
import com.aagam.product.repository.ProductRepository;
import com.aagam.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDto> getAllProducts(String search) {
        List<Product> products = (search != null && !search.isBlank()) ?
                productRepository.findByNameContainingIgnoreCase(search) :
                productRepository.findAll();
        return ProductMapper.toResponseDtoList(products);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductMapper.toResponseDto(product);
    }

    @Override
    public boolean isProductNameExists(String name) {
        return productRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public List<ProductResponseDto> saveProducts(List<ProductRequestDto> products) {

        // Validate duplicate names in request
        Set<String> uniqueNames = new HashSet<>();
        for (ProductRequestDto dto : products) {
            if (!uniqueNames.add(dto.name())) {
                throw new IllegalArgumentException("Duplicate product name in request: " + dto.name());
            }
        }

        // Validate existing products
        List<String> names = products.stream().map(ProductRequestDto::name).toList();
        List<String> existingNames = productRepository.findAllByNameIn(names)
                .stream()
                .map(Product::getName)
                .toList();

        if (!existingNames.isEmpty()) {
            throw new IllegalArgumentException("Products already exist: " + existingNames);
        }

        List<Product> entities = products.stream()
                .map(dto -> {
                    Category category = categoryRepository.findByName(dto.categoryName())
                            .orElseThrow(() -> new RuntimeException("Category not found: " + dto.categoryName()));
                    return ProductMapper.toEntity(dto, category);
                })
                .collect(Collectors.toList());

        List<Product> saved = productRepository.saveAll(entities);
        return ProductMapper.toResponseDtoList(saved);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        Category category = categoryRepository.findByName(dto.categoryName())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.categoryName()));

        existingProduct.setName(dto.name());
        existingProduct.setImage(dto.image());
        existingProduct.setVisible(dto.visible());
        existingProduct.setStatus(dto.status() != null ?
                com.aagam.product.entity.ProductStatus.valueOf(dto.status()) : existingProduct.getStatus());
        existingProduct.setCategory(category);

        // Update variants
        existingProduct.getVariants().clear();
        List varList = dto.variants() == null ? List.of() : dto.variants().stream()
                .map(v -> {
                    var variant = com.aagam.product.mapper.VariantMapper.toEntity(v);
                    variant.setProduct(existingProduct);
                    return variant;
                })
                .collect(Collectors.toList());

        existingProduct.getVariants().addAll(varList);

        return ProductMapper.toResponseDto(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}