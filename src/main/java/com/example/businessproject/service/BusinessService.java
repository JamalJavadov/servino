package com.example.businessproject.service;

import com.example.businessproject.exception.BusinessNotFoundException;
import com.example.businessproject.model.dto.business.BusinessRequestDto;
import com.example.businessproject.model.dto.business.BusinessResponseDto;
import com.example.businessproject.model.dto.business.BusinessUpdateDto;
import com.example.businessproject.model.entity.Business;
import com.example.businessproject.model.mapper.BusinessMapper;
import com.example.businessproject.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;


    public BusinessResponseDto updateBusiness(BusinessUpdateDto businessUpdateDto){
        Business business = businessRepository.findBusinessesByContactMail(businessUpdateDto.getContactMail()).orElseThrow(()->new BusinessNotFoundException("Business Not Found"));
        Business updatedBusiness = businessMapper.toEntity(businessUpdateDto);
        updatedBusiness.setId(business.getId());//bu hisseni deyisersen
        return businessMapper.toDto(businessRepository.save(updatedBusiness));
    }
}
