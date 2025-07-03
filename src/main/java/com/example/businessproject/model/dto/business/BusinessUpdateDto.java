package com.example.businessproject.model.dto.business;

import com.example.businessproject.model.dto.product.ProductRequestDto;
import com.example.businessproject.model.dto.product.ProductUpdateDto;
import com.example.businessproject.model.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUpdateDto {
    @NotBlank(message = "BusinessName Can not be empty")
    @Pattern(
            regexp = "^[^<>#%]{0,50}$",
            message = "Text must be up to 50 characters and must not contain <, >, #, % symbols"
    )
    private String businessName;

    @NotBlank(message = "Location Can not be empty")
    private String businessLocation;

    private String description;


    private String logoUrl;

    @Email(message = "Email Must be valid")
    @NotBlank(message = "Email Can not be empty")
    private String contactMail;

    @NotBlank(message = "Number can not be empty")
    private String contactNumber;

    private String category;
}
