package com.example.businessproject.model.mapper;

import com.example.businessproject.model.dto.comment.CommentRequestDto;
import com.example.businessproject.model.dto.comment.CommentResponseDto;
import com.example.businessproject.model.dto.comment.CommentUpdateDto;
import com.example.businessproject.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = ProductMapper.class)
public interface CommentMapper {
    Comment toEntity(CommentRequestDto commentRequestDto);

    CommentResponseDto toDto(Comment comment);

    Comment toEntity(CommentUpdateDto commentUpdateDto);
}
