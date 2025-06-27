package com.example.businessproject.model.dto.product;

import com.example.businessproject.model.dto.business.BusinessResponseDto;
import com.example.businessproject.model.dto.comment.CommentResponseDto;
import com.example.businessproject.model.entity.Business;
import com.example.businessproject.model.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private long id;
    private String name;

    private String type;

    private List<CommentResponseDto> comments;

    private Double rating;

    private LocalDateTime localDateTime;

    private Double price;

    private Integer totatUsedCount;

    private BusinessResponseDto business;

    private String imageUrl;

}
