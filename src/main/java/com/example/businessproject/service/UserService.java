package com.example.businessproject.service;

import com.example.businessproject.exception.UserNotFoundException;
import com.example.businessproject.model.dto.user.UserResponseDto;
import com.example.businessproject.model.dto.user.UserUpdateDto;
import com.example.businessproject.model.entity.User;
import com.example.businessproject.model.mapper.UserMapper;
import com.example.businessproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto getUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User Not Found"));
        System.out.println(userMapper.toDto(user).getEmail());
        return userMapper.toDto(user);
    }


    public UserResponseDto updateUser(UserUpdateDto updateUserDto){
        User user = userRepository.findByEmail(updateUserDto.getEmail()).orElseThrow(()->new UserNotFoundException("User Not Found"));
        user = userMapper.updateUserFromDto(updateUserDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

}
