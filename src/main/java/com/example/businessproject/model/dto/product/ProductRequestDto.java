package com.example.businessproject.model.dto.product;

import com.example.businessproject.model.dto.business.BusinessRequestDto;
import com.example.businessproject.model.entity.Business;
import com.example.businessproject.model.entity.Comment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
