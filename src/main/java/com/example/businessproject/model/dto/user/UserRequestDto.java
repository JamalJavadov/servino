package com.example.businessproject.model.dto.user;

import com.example.businessproject.model.entity.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Validasyalar
public class UserRequestDto {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;
}
