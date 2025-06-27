package com.example.businessproject.service;

import com.example.businessproject.exception.ProductNotFoundException;
import com.example.businessproject.model.dto.product.ProductRequestDto;
import com.example.businessproject.model.dto.product.ProductResponseDto;
import com.example.businessproject.model.dto.product.ProductUpdateDto;
import com.example.businessproject.model.entity.Product;
import com.example.businessproject.model.mapper.ProductMapper;
import com.example.businessproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        Product product = productMapper.toEntity(productRequestDto);
        return productMapper.toDto(productRepository.save(product));
    }

    public ProductResponseDto updateProduct(ProductUpdateDto productUpdateDto){
        Product product = productRepository.findProductById(productUpdateDto.getId()).orElseThrow(()->new ProductNotFoundException("Product Do Not Found"));
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productUpdateDto)));//bura baxarsan
    }



}
