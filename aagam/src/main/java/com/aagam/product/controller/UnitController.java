package com.aagam.product.controller;

import com.aagam.product.dto.UnitRequestDto;
import com.aagam.product.dto.UnitResponseDto;
import com.aagam.product.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    // Get all units
    @GetMapping
    public ResponseEntity<List<UnitResponseDto>> getAllUnits() {
        return ResponseEntity.ok(unitService.getAllUnits());
    }

    // Get unit by id
    @GetMapping("/{id}")
    public ResponseEntity<UnitResponseDto> getUnitById(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.getUnitById(id));
    }

    // Create unit
    @PostMapping
    public ResponseEntity<UnitResponseDto> createUnit(@RequestBody UnitRequestDto dto) {
        UnitResponseDto created = unitService.createUnit(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Update unit
    @PutMapping("/{id}")
    public ResponseEntity<UnitResponseDto> updateUnit(@PathVariable Long id,
                                                      @RequestBody UnitRequestDto dto) {
        return ResponseEntity.ok(unitService.updateUnit(id, dto));
    }

    // Delete unit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }
}