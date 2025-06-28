package com.example.businessproject.model.dto.product;

import com.example.businessproject.model.dto.business.BusinessRequestDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String name;

    private String type;

    private Double price;

    private BusinessRequestDto business;
}
