package com.example.businessproject.model.mapper;

import com.example.businessproject.model.dto.product.ProductRequestDto;
import com.example.businessproject.model.dto.product.ProductResponseDto;
import com.example.businessproject.model.dto.product.ProductUpdateDto;
import com.example.businessproject.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {CommentMapper.class,BusinessMapper.class})
public interface ProductMapper {
    Product toEntity(ProductRequestDto productRequestDto);

    ProductResponseDto toDto(Product product);

    Product toEntity(ProductUpdateDto productUpdateDto);
}
