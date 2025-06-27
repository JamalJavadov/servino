package com.example.businessproject.model.mapper;

import com.example.businessproject.model.dto.business.BusinessRequestDto;
import com.example.businessproject.model.dto.business.BusinessResponseDto;
import com.example.businessproject.model.dto.business.BusinessUpdateDto;
import com.example.businessproject.model.entity.Business;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = ProductMapper.class)
public interface BusinessMapper {
    Business toEntity(BusinessRequestDto businessRequestDto);

    BusinessResponseDto toDto(Business business);

    Business toEntity(BusinessUpdateDto businessUpdateDto);
}
