package com.aagam.product.dto;

import lombok.Builder;

@Builder
public record UnitResponseDto(
        Long id,
        String name
){}