package com.example.businessproject.model.dto.product;

import com.example.businessproject.model.dto.business.BusinessUpdateDto;
import com.example.businessproject.model.dto.comment.CommentUpdateDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private long id;

    private String name;

    private String type;

    private List<CommentUpdateDto> comments;

    private Double rating;

    private Double price;

    private Integer totatUsedCount;

    private String imageUrl;

    private BusinessUpdateDto businessUpdateDto ;
}
