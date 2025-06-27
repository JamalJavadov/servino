package com.example.businessproject.model.dto.user;

import com.example.businessproject.model.dto.comment.CommentUpdateDto;
import com.example.businessproject.model.dto.product.ProductUpdateDto;
import com.example.businessproject.model.entity.Comment;
import com.example.businessproject.model.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<CommentUpdateDto> comments;

    private List<ProductUpdateDto> products;

    private String phoneNumber;

    private String avatarUrl;
}
