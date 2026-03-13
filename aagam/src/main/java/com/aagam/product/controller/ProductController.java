package com.aagam.product.controller;

import com.aagam.product.dto.ProductNameCheckDto;
import com.aagam.product.dto.ProductRequestDto;
import com.aagam.product.dto.ProductResponseDto;
import com.aagam.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Get all products (optional search)
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(@RequestParam(required = false) String search) {
        List<ProductResponseDto> products = productService.getAllProducts(search);
        return ResponseEntity.ok(products);
    }

    // Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Check if product name exists
    @GetMapping("/check-name")
    public ResponseEntity<ProductNameCheckDto> checkName(@RequestParam String name) {
        boolean exists = productService.isProductNameExists(name);
        return ResponseEntity.ok(ProductNameCheckDto.builder().name(name).exists(exists).build());
    }

    // Bulk create products
    @PostMapping("/bulk")
    public ResponseEntity<List<ProductResponseDto>> saveProducts(@RequestBody List<ProductRequestDto> products) {
        System.out.println(products.toString()+ "     PRODUCTS");
        System.out.println(products.get(0).categoryName());
        List<ProductResponseDto> saved = productService.saveProducts(products);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Update product by id
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
                                                            @RequestBody ProductRequestDto dto) {
        ProductResponseDto updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete product by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}