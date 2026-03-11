package com.aagam.product.service;

import com.aagam.product.dto.UnitRequestDto;
import com.aagam.product.dto.UnitResponseDto;

import java.util.List;

public interface UnitService {

    List<UnitResponseDto> getAllUnits();

    UnitResponseDto getUnitById(Long id);

    UnitResponseDto createUnit(UnitRequestDto dto);

    UnitResponseDto updateUnit(Long id, UnitRequestDto dto);

    void deleteUnit(Long id);
}