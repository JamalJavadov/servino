package com.example.businessproject.controller;

import com.example.businessproject.model.dto.auth.AuthenticationRequestDto;
import com.example.businessproject.model.dto.product.ProductRequestDto;
import com.example.businessproject.model.dto.product.ProductResponseDto;
import com.example.businessproject.model.dto.product.ProductUpdateDto;
import com.example.businessproject.service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductServices productServices;

    @PreAuthorize("hasRole('BUSINESSMAN')")
    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto){
       return ResponseEntity.ok(productServices.createProduct(productRequestDto));
    }

    @PreAuthorize("hasRole('BUSINESSMAN')")
    @PutMapping("/update")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductUpdateDto productUpdateDto){
        return ResponseEntity.ok(productServices.updateProduct(productUpdateDto));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get-products")
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        return ResponseEntity.ok(productServices.getAll());
    }

    @PreAuthorize("hasRole('BUSINESSMAN')")
    @PostMapping("/get-business-products")
    public ResponseEntity<List<ProductResponseDto>> getBusinessProducts(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        return ResponseEntity.ok(productServices.getBusinessProducts(authenticationRequestDto));
    }


}
