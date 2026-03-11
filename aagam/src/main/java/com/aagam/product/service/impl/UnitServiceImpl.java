package com.aagam.product.service.impl;

import com.aagam.product.dto.UnitRequestDto;
import com.aagam.product.dto.UnitResponseDto;
import com.aagam.product.entity.Unit;
import com.aagam.product.mapper.UnitMapper;
import com.aagam.product.repository.UnitRepository;
import com.aagam.product.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    @Override
    public List<UnitResponseDto> getAllUnits() {
        return UnitMapper.toResponseDtoList(unitRepository.findAll());
    }

    @Override
    public UnitResponseDto getUnitById(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found with id: " + id));
        return UnitMapper.toResponseDto(unit);
    }

    @Override
    public UnitResponseDto createUnit(UnitRequestDto dto) {
        Unit unit = UnitMapper.toEntity(dto);
        return UnitMapper.toResponseDto(unitRepository.save(unit));
    }

    @Override
    public UnitResponseDto updateUnit(Long id, UnitRequestDto dto) {
        Unit existing = unitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found with id: " + id));
        existing.setName(dto.name());
        return UnitMapper.toResponseDto(existing);
    }

    @Override
    public void deleteUnit(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found with id: " + id));
        unitRepository.delete(unit);
    }
}