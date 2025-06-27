package com.example.businessproject.model.dto.user;

import com.example.businessproject.model.dto.comment.CommentResponseDto;
import com.example.businessproject.model.entity.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String avatarUrl;
    private String phoneNumber;
    private List<CommentResponseDto> comments;
    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
