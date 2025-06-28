package com.example.businessproject.service.securityservice;

import com.example.businessproject.exception.BusinessNotFoundException;
import com.example.businessproject.exception.CodeExpire;
import com.example.businessproject.model.dto.auth.AuthenticationRequestDto;
import com.example.businessproject.model.dto.auth.AuthenticationResponseDto;
import com.example.businessproject.model.dto.business.BusinessRequestDto;
import com.example.businessproject.model.dto.code.AuthCodeVerficationDto;
import com.example.businessproject.model.entity.Business;
import com.example.businessproject.repository.BusinessRepository;
import com.example.businessproject.service.notification.EmailService;
import com.example.businessproject.service.notification.VerificationCodeStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class BusinessAuthenticationService {
    private final BusinessRepository businessRepository;
    private final EmailService emailService;
    private final VerificationCodeStore verificationCodeStore;
    private final JwtService jwtService;
    private final BusinessRefreshTokenService businessRefreshTokenService;
    private final PasswordEncoder passwordEncoder;


    public String register(BusinessRequestDto businessRequestDto){
        Business business = Business.builder()
                .businessName(businessRequestDto.getBusinessName())
                .businessLocation(businessRequestDto.getBusinessLocation())
                .description(businessRequestDto.getDescription())
                .contactMail(businessRequestDto.getContactMail())
                .contactNumber(businessRequestDto.getContactNumber())
                .category(businessRequestDto.getCategory())
                .password(passwordEncoder.encode(businessRequestDto.getPassword()))
                .build();
        businessRepository.save(business);
        return "Register Successfully";
    }

    public String authenticateSendCode(AuthenticationRequestDto request) {
        String code = String.valueOf(new Random().nextInt(900000)+100000);
        verificationCodeStore.saveCode(request.getEmail(),code);
        emailService.sendVerificationCode(request.getEmail(),code);
        return "Verification Code Send";
    }

    public AuthenticationResponseDto verifyCode(AuthCodeVerficationDto authCodeVerficationDto){
        if (verificationCodeStore.isCodeValid(authCodeVerficationDto.getEmail(),authCodeVerficationDto.getCode())){
            verificationCodeStore.removeCode(authCodeVerficationDto.getEmail());
            Business business = businessRepository.findBusinessesByContactMail(authCodeVerficationDto.getEmail()).orElseThrow(()->new BusinessNotFoundException("Business Not Found"));
            var jwtToken = jwtService.generatedToken(business);
            String refreshToken = businessRefreshTokenService.createRefreshToken(business).getToken();
            return AuthenticationResponseDto.builder().token(jwtToken).refreshToken(refreshToken).build();
        } else {
            throw new CodeExpire("code tap tap");
        }
    }
}
