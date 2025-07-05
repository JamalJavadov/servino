package com.example.businessproject.service;

import com.example.businessproject.exception.BusinessNotFoundException;
import com.example.businessproject.exception.ProductNotFoundException;
import com.example.businessproject.model.dto.product.ProductRequestDto;
import com.example.businessproject.model.dto.product.ProductResponseDto;
import com.example.businessproject.model.dto.product.ProductUpdateDto;
import com.example.businessproject.model.entity.Business;
import com.example.businessproject.model.entity.Product;
import com.example.businessproject.model.mapper.ProductMapper;
import com.example.businessproject.repository.BusinessRepository;
import com.example.businessproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BusinessRepository businessRepository;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        Business business = businessRepository.findBusinessesByContactMail(productRequestDto.getBusinessMail()).orElseThrow(()->new BusinessNotFoundException("Business Not Found"));
        Product product = productMapper.toEntity(productRequestDto);
        product.setBusiness(business);
        return productMapper.toDto(productRepository.save(product));
    }

    public ProductResponseDto updateProduct(ProductUpdateDto productUpdateDto){
        Product product = productRepository.findProductById(productUpdateDto.getId()).orElseThrow(()->new ProductNotFoundException("Product Do Not Found"));
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productUpdateDto,product)));
    }

    public List<ProductResponseDto> getAll(){
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    public List<ProductResponseDto> getBusinessProducts(String contactMail){
        Business business = businessRepository.findBusinessesByContactMail(contactMail).orElseThrow(()->new BusinessNotFoundException("Business Not Found"));
            return business.getProducts().stream()
                    .map(productMapper::toDto)
                    .toList();
        }

    }
