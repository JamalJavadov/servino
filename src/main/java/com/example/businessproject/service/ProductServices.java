package com.example.businessproject.service;

import com.example.businessproject.exception.BusinessNotFoundException;
import com.example.businessproject.exception.ProductNotFoundException;
import com.example.businessproject.model.dto.auth.AuthenticationRequestDto;
import com.example.businessproject.model.dto.product.ProductRequestDto;
import com.example.businessproject.model.dto.product.ProductResponseDto;
import com.example.businessproject.model.dto.product.ProductUpdateDto;
import com.example.businessproject.model.entity.Business;
import com.example.businessproject.model.entity.Product;
import com.example.businessproject.model.mapper.BusinessMapper;
import com.example.businessproject.model.mapper.ProductMapper;
import com.example.businessproject.repository.BusinessRepository;
import com.example.businessproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BusinessRepository businessRepository;
    private final PasswordEncoder passwordEncoder;
    private final BusinessMapper businessMapper;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        Business business = businessRepository.findBusinessesByContactMail(productRequestDto.getBusinessMail()).orElseThrow(()->new BusinessNotFoundException("Business Not Found"));
        Product product = productMapper.toEntity(productRequestDto);
        product.setBusiness(business);
        return productMapper.toDto(productRepository.save(product));
    }

    public ProductResponseDto updateProduct(ProductUpdateDto productUpdateDto){
        Product product = productRepository.findProductById(productUpdateDto.getId()).orElseThrow(()->new ProductNotFoundException("Product Do Not Found"));
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productUpdateDto)));//bura baxarsan
    }

    public List<ProductResponseDto> getAll(){
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    public List<ProductResponseDto> getBusinessProducts(AuthenticationRequestDto authenticationRequestDto){
        Business business = businessRepository.findBusinessesByContactMail(authenticationRequestDto.getEmail()).orElseThrow(()->new BusinessNotFoundException("Business Not Found"));
        if (passwordEncoder.matches(authenticationRequestDto.getPassword(), business.getPassword())){
            return business.getProducts().stream()
                    .map(productMapper::toDto)
                    .toList();
        }else {
            throw new BusinessNotFoundException("Password is not true");
        }

    }



}
