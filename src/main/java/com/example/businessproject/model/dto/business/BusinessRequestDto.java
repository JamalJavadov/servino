package com.example.businessproject.model.dto.business;

import com.example.businessproject.model.entity.Product;
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
public class BusinessRequestDto {
    private String password;

    private String businessName;

    private String businessLocation;

    private String description;

    private String contactMail;

    private String contactNumber;

    private String category;
}
