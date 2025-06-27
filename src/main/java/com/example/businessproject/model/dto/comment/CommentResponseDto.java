package com.example.businessproject.model.dto.comment;

import com.example.businessproject.model.dto.product.ProductResponseDto;
import com.example.businessproject.model.dto.user.UserResponseDto;
import com.example.businessproject.model.entity.Product;
import com.example.businessproject.model.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private long id;

    private String message;

    private LocalDateTime creationAt;

    private ProductResponseDto productResponseDto;

    private UserResponseDto user;
}
