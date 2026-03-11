package com.aagam.product.dto;

import lombok.Builder;

@Builder
public record VariantResponseDto(
        String weight,
        String unitName,
        double price,
        int stock
){}