package com.example.businessproject.model.mapper;

import com.example.businessproject.model.dto.user.UserUpdateDto;
import com.example.businessproject.model.dto.user.UserRequestDto;
import com.example.businessproject.model.dto.user.UserResponseDto;
import com.example.businessproject.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, ProductMapper.class})
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User toEntity(UserRequestDto userRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserResponseDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromDto(UserUpdateDto dto, @MappingTarget User user);
}

