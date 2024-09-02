package com.alfred.product.Controller;

import com.alfred.product.DTO.ProductPurchaseResponse;
import com.alfred.product.DTO.ProductPusrchaseRequest;
import com.alfred.product.DTO.ProductRequest;
import com.alfred.product.DTO.ProductResponse;
import com.alfred.product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPusrchaseRequest> request
    ) {
        return ResponseEntity.ok(productService.purchaseProduct(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
