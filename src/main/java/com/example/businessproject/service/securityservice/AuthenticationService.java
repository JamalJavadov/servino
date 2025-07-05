package com.example.businessproject.service.securityservice;


import com.example.businessproject.exception.*;
import com.example.businessproject.model.dto.auth.AuthenticationRequestDto;
import com.example.businessproject.model.dto.auth.AuthenticationResponseDto;
import com.example.businessproject.model.dto.business.BusinessRequestDto;
import com.example.businessproject.model.dto.code.AuthCodeVerficationDto;
import com.example.businessproject.model.dto.user.UserRequestDto;
import com.example.businessproject.model.entity.Business;
import com.example.businessproject.model.entity.Role;
import com.example.businessproject.model.entity.User;
import com.example.businessproject.repository.BusinessRepository;
import com.example.businessproject.repository.UserRepository;
import com.example.businessproject.service.notification.EmailService;
import com.example.businessproject.service.notification.VerificationCodeStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final VerificationCodeStore verificationCodeStore;
    private final UserRefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final BusinessRefreshTokenService businessRefreshTokenService;

    public String userRegister(UserRequestDto request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return "Register Successfully";
    }
    public String businessRegister(BusinessRequestDto businessRequestDto){
        Business business = Business.builder()
                .businessName(businessRequestDto.getBusinessName())
                .businessLocation(businessRequestDto.getBusinessLocation())
                .description(businessRequestDto.getDescription())
                .contactMail(businessRequestDto.getContactMail())
                .contactNumber(businessRequestDto.getContactNumber())
                .category(businessRequestDto.getCategory())
                .password(passwordEncoder.encode(businessRequestDto.getPassword()))
                .role(Role.BUSINESSMAN)
                .build();
        businessRepository.save(business);
        return "Register Successfully";
    }

    public String authenticateSendCode(AuthenticationRequestDto request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        Optional<Business> business = businessRepository.findBusinessesByContactMail(request.getEmail());
        if (user.isPresent() || business.isPresent()){
        String code = String.valueOf(new Random().nextInt(900000)+100000);
        verificationCodeStore.saveCode(request.getEmail(),code);
        emailService.sendVerificationCode(request.getEmail(),code);
        return "Verification Code Send";
        }else {
            throw new EmailNotFound("Not Found");
        }
    }

    public AuthenticationResponseDto verifyCode(AuthCodeVerficationDto authCodeVerfication) {
        if (verificationCodeStore.isCodeValid(authCodeVerfication.getEmail(), authCodeVerfication.getCode())) {
            verificationCodeStore.removeCode(authCodeVerfication.getEmail());
            Optional<User> user = userRepository.findByEmail(authCodeVerfication.getEmail());
            Optional<Business> business = businessRepository.findBusinessesByContactMail(authCodeVerfication.getEmail());
            if (user.isPresent()){
                user.get().setVerified(true);
                userRepository.save(user.get());
                var jwtToken = jwtService.generatedToken(user.get());
                String refreshToken = refreshTokenService.createRefreshToken(user.get()).getToken();
                return AuthenticationResponseDto.builder().token(jwtToken).refreshToken(refreshToken).build();
            }else if(business.isPresent()){
                verificationCodeStore.removeCode(authCodeVerfication.getEmail());
                var jwtToken = jwtService.generatedToken(business.get());
                String refreshToken = businessRefreshTokenService.createRefreshToken(business.get()).getToken();
                return AuthenticationResponseDto.builder().token(jwtToken).refreshToken(refreshToken).build();
            }else {
                throw new VerifyCodeFailedException("Email or Code is wrong");
            }
        } else {
            throw new CodeExpireException("Code expire try to send code again");
        }
    }
}
