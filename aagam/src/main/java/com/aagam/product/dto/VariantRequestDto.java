package com.aagam.product.dto;

import lombok.Builder;

@Builder
public record VariantRequestDto(
        String weight,
        String unitName,
        double price,
        int stock
){}