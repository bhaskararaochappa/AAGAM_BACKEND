package com.aagam.product.mapper;

import com.aagam.product.dto.ProductRequestDto;
import com.aagam.product.dto.ProductResponseDto;
import com.aagam.product.entity.Category;
import com.aagam.product.entity.Product;
import com.aagam.product.entity.ProductStatus;
import com.aagam.product.entity.Variant;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    // Entity → Response DTO
    public static ProductResponseDto toResponseDto(Product product) {
        if (product == null) return null;

        List<Variant> variants = product.getVariants() != null ? product.getVariants() : List.of();

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .status(product.getStatus() != null ? product.getStatus().name() : null)
                .visible(product.isVisible())
                .variants(VariantMapper.toResponseDtoList(variants))
                .build();
    }

    public static List<ProductResponseDto> toResponseDtoList(List<Product> products) {
        if (products == null) return List.of();
        return products.stream().map(ProductMapper::toResponseDto).collect(Collectors.toList());
    }

    // Request DTO → Entity
    public static Product toEntity(ProductRequestDto dto, Category category) {
        if (dto == null) return null;

        Product product = Product.builder()
                .name(dto.name())
                .image(dto.image())
                .visible(dto.visible())
                .status(dto.status() != null ? com.aagam.product.entity.ProductStatus.valueOf(dto.status()) : ProductStatus.IN_STOCK)
                .category(category)
                .build();

        List<Variant> variants = VariantMapper.toEntityList(dto.variants());
        variants.forEach(v -> v.setProduct(product));
        product.setVariants(variants);

        return product;
    }

    public static List<Product> toEntityList(List<ProductRequestDto> dtos, Category category) {
        if (dtos == null) return List.of();
        return dtos.stream().map(dto -> toEntity(dto, category)).collect(Collectors.toList());
    }
}