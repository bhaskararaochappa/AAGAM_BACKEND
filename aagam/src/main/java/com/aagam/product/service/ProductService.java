package com.aagam.product.service;

import com.aagam.product.dto.ProductRequestDto;
import com.aagam.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAllProducts(String search);

    ProductResponseDto getProductById(Long id);

    boolean isProductNameExists(String name);

    List<ProductResponseDto> saveProducts(List<ProductRequestDto> products);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    void deleteProduct(Long id);
}