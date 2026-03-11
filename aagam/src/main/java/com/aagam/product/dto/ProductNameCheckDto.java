package com.aagam.product.dto;

import lombok.Builder;

@Builder
public record ProductNameCheckDto(
        String name,
        boolean exists
){}