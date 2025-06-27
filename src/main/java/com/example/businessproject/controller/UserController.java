package com.example.businessproject.controller;

import com.example.businessproject.model.dto.user.UserResponseDto;
import com.example.businessproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }
}
