package com.aagam.product.dto;

import lombok.Builder;

@Builder
public record CategoryResponseDto(
        Long id,
        String name
){}