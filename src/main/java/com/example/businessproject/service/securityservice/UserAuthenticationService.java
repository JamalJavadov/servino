package com.example.businessproject.service.securityservice;


import com.example.businessproject.exception.CodeExpire;
import com.example.businessproject.exception.UserNotFoundException;
import com.example.businessproject.model.dto.user.UserRequestDto;
import com.example.businessproject.model.entity.Role;
import com.example.businessproject.model.entity.User;
import com.example.businessproject.repository.UserRepository;
import com.example.businessproject.model.dto.auth.AuthenticationRequestDto;
import com.example.businessproject.model.dto.code.AuthCodeVerficationDto;
import com.example.businessproject.model.dto.auth.AuthenticationResponseDto;
import com.example.businessproject.service.notification.EmailService;
import com.example.businessproject.service.notification.VerificationCodeStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final VerificationCodeStore verificationCodeStore;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public String register(UserRequestDto request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(Role.USER)
                .build();
        repository.save(user);
        return "Register Successfully";
    }

    public String authenticateSendCode(AuthenticationRequestDto request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
        String code = String.valueOf(new Random().nextInt(900000)+100000);
        verificationCodeStore.saveCode(request.getEmail(),code);
        emailService.sendVerificationCode(request.getEmail(),code);
        return "Verification Code Send";

        }else {
            throw new UserNotFoundException("User Not Found");
        }
    }

    public AuthenticationResponseDto verifyCode(AuthCodeVerficationDto authCodeVerfication) {
        if (verificationCodeStore.isCodeValid(authCodeVerfication.getEmail(), authCodeVerfication.getCode())) {
            verificationCodeStore.removeCode(authCodeVerfication.getEmail());
            User user = repository.findByEmail(authCodeVerfication.getEmail()).orElseThrow(()->new UserNotFoundException("User not found with this email"));
            user.setVerified(true);
            userRepository.save(user);
            var jwtToken = jwtService.generatedToken(user);
            String refreshToken = refreshTokenService.createRefreshToken(user).getToken();
            return AuthenticationResponseDto.builder().token(jwtToken).refreshToken(refreshToken).build();
        } else {
            throw new CodeExpire("code tap tap");
        }
    }
}
