package com.example.businessproject.model.dto.business;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRequestDto {

    @NotBlank(message = "Password Can not be empty")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*[<>#%])[A-Za-z0-9!@\\$\\^\\&\\*\\(\\)_\\+\\-=\\{\\}\\[\\]:;\"'\\|\\\\,.\\?/]{8,15}$",
            message = "Password must be 8-15 characters, include at least one digit, one uppercase letter, one lowercase letter, and not contain <, >, #, % symbols"
    )
    private String password;

    @NotBlank(message = "BusinessName Can not be empty")
    @Pattern(
            regexp = "^[^<>#%]{0,50}$",
            message = "Text must be up to 50 characters and must not contain <, >, #, % symbols"
    )
    private String businessName;

    @NotBlank(message = "Location Can not be empty")
    private String businessLocation;

    private String description;

    @Email(message = "Email Must be valid")
    @NotBlank(message = "Email Can not be empty")
    private String contactMail;

    @NotBlank(message = "Number can not be empty")
    private String contactNumber;

    //burani secim olaraq etmek isteyirem
    private String category;
}
