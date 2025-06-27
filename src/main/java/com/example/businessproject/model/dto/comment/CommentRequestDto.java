package com.example.businessproject.model.dto.comment;

import com.example.businessproject.model.dto.product.ProductRequestDto;
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
public class CommentRequestDto {
    private String message;

    private ProductRequestDto productRequestDto;

    private User user;
}
