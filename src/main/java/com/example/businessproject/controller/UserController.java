package com.example.businessproject.controller;

import com.example.businessproject.model.dto.user.UserResponseDto;
import com.example.businessproject.model.dto.user.UserUpdateDto;
import com.example.businessproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get-via-email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestParam UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.updateUser(userUpdateDto));
    }


}
