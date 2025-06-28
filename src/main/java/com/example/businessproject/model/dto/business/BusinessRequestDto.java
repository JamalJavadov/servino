package com.example.businessproject.model.dto.business;

import lombok.*;

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
