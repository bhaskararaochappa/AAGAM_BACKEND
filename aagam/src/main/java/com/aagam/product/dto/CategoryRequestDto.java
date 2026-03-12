package com.aagam.product.dto;

import lombok.Builder;

@Builder
public record CategoryRequestDto(
        String name
){}