package com.aagam.product.mapper;

import com.aagam.product.dto.VariantRequestDto;
import com.aagam.product.dto.VariantResponseDto;
import com.aagam.product.entity.Unit;
import com.aagam.product.entity.Variant;

import java.util.List;
import java.util.stream.Collectors;

public class VariantMapper {

    // Entity → Response DTO
    public static VariantResponseDto toResponseDto(Variant variant) {
        if (variant == null) return null;
        return VariantResponseDto.builder()
                .weight(variant.getWeight())
                .unitName(variant.getUnit() != null ? variant.getUnit().getName() : null)
                .price(variant.getPrice())
                .stock(variant.getStock())
                .build();
    }

    public static List<VariantResponseDto> toResponseDtoList(List<Variant> variants) {
        if (variants == null) return List.of();
        return variants.stream().map(VariantMapper::toResponseDto).collect(Collectors.toList());
    }

    // Request DTO → Entity
    public static Variant toEntity(VariantRequestDto dto) {
        if (dto == null) return null;
        Variant variant = Variant.builder()
                .weight(dto.weight())
                .price(dto.price())
                .stock(dto.stock())
                .build();

        if (dto.unitName() != null) {
            variant.setUnit(Unit.builder().name(dto.unitName()).build());
        }

        return variant;
    }

    public static List<Variant> toEntityList(List<VariantRequestDto> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream().map(VariantMapper::toEntity).collect(Collectors.toList());
    }
}