package com.example.businessproject.model.dto.comment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    private String message;

    private long productId;

    private String userGmail;
}
