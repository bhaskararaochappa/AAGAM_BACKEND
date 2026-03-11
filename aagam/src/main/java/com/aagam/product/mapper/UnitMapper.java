package com.aagam.product.mapper;

import com.aagam.product.dto.UnitRequestDto;
import com.aagam.product.dto.UnitResponseDto;
import com.aagam.product.entity.Unit;

import java.util.List;
import java.util.stream.Collectors;

public class UnitMapper {

    // Entity → Response DTO
    public static UnitResponseDto toResponseDto(Unit unit) {
        if (unit == null) return null;
        return UnitResponseDto.builder()
                .id(unit.getId())
                .name(unit.getName())
                .build();
    }

    public static List<UnitResponseDto> toResponseDtoList(List<Unit> units) {
        if (units == null) return List.of();
        return units.stream().map(UnitMapper::toResponseDto).collect(Collectors.toList());
    }

    // Request DTO → Entity
    public static Unit toEntity(UnitRequestDto dto) {
        if (dto == null) return null;
        return Unit.builder()
                .name(dto.name())
                .build();
    }

    public static List<Unit> toEntityList(List<UnitRequestDto> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream().map(UnitMapper::toEntity).collect(Collectors.toList());
    }
}