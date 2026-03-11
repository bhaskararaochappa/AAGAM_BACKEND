package com.aagam.product.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record ProductResponseDto(
        Long id,
        String name,
        String image,
        String categoryName,
        String status,
        boolean visible,
        List<VariantResponseDto> variants
){}