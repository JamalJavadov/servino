package com.example.businessproject.model.mapper;

import com.example.businessproject.model.dto.business.BusinessRequestDto;
import com.example.businessproject.model.dto.business.BusinessResponseDto;
import com.example.businessproject.model.dto.business.BusinessUpdateDto;
import com.example.businessproject.model.entity.Business;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Business toEntity(BusinessRequestDto businessRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BusinessResponseDto toDto(Business business);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Business toEntity(BusinessUpdateDto businessUpdateDto, @MappingTarget Business business);
}

