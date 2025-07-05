package com.example.businessproject.controller;

import com.example.businessproject.model.dto.business.BusinessResponseDto;
import com.example.businessproject.model.dto.business.BusinessUpdateDto;
import com.example.businessproject.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/business")
public class BusinessController {
    private final BusinessService businessService;

    @PreAuthorize("hasRole('BUSINESSMAN')")
    @PutMapping("/update")
    public ResponseEntity<BusinessResponseDto> updateBusiness(@RequestBody BusinessUpdateDto businessUpdateDto){
        return ResponseEntity.ok(businessService.updateBusiness(businessUpdateDto));
    }






}
