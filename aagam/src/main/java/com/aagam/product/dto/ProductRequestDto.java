package com.aagam.product.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record ProductRequestDto(
        String name,
        String image,
        String categoryName,
        String status,
        boolean visible,
        List<VariantRequestDto> variants
){}