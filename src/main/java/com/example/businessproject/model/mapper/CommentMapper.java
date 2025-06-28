package com.example.businessproject.model.mapper;

import com.example.businessproject.model.dto.comment.CommentRequestDto;
import com.example.businessproject.model.dto.comment.CommentResponseDto;
import com.example.businessproject.model.dto.comment.CommentUpdateDto;
import com.example.businessproject.model.entity.Comment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment toEntity(CommentRequestDto commentRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommentResponseDto toDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment toEntity(CommentUpdateDto commentUpdateDto);
}

