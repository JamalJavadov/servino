package com.example.businessproject.controller;

import com.example.businessproject.model.dto.auth.AuthenticationRequestDto;
import com.example.businessproject.model.dto.auth.AuthenticationResponseDto;
import com.example.businessproject.model.dto.business.BusinessRequestDto;
import com.example.businessproject.model.dto.code.AuthCodeVerficationDto;
import com.example.businessproject.model.dto.refreshtoken.TokenRefreshRequestDto;
import com.example.businessproject.model.dto.refreshtoken.TokenRefreshResponseDto;
import com.example.businessproject.model.entity.Business;
import com.example.businessproject.repository.BusinessRefreshTokenRepository;
import com.example.businessproject.service.securityservice.AuthenticationService;
import com.example.businessproject.service.securityservice.BusinessRefreshTokenService;
import com.example.businessproject.service.securityservice.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authbusiness")
public class BusinessAuthenticationController {
    private final BusinessRefreshTokenService businessRefreshTokenService;
    private final AuthenticationService authenticationService;
    private final BusinessRefreshTokenRepository businessRefreshTokenRepository;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody BusinessRequestDto businessRequestDto){
        return ResponseEntity.ok(authenticationService.businessRegister(businessRequestDto));
    }

    @PostMapping("/send-code")
    public ResponseEntity<String> authenticate(@Valid @RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticateSendCode(request));
    }

    @PostMapping("/verify-code")
    public ResponseEntity<AuthenticationResponseDto> verifyCode(@Valid @RequestBody AuthCodeVerficationDto authCodeVerfication) {
        return ResponseEntity.ok(authenticationService.verifyCode(authCodeVerfication));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequestDto tokenRefresh){
        return businessRefreshTokenRepository.findRefreshTokenByToken(tokenRefresh.getRefreshToken())
                .map(token->{
                    if (businessRefreshTokenService.isExpired(token)){
                        businessRefreshTokenRepository.delete(token);
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired. Please log in again.");
                    }
                    Business business = token.getBusiness();
                    String newAccessToken = jwtService.generatedToken(business);
                    return ResponseEntity.ok(new TokenRefreshResponseDto(newAccessToken,token.getToken()));

                })
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refresh Token Not Found"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody TokenRefreshRequestDto tokenRefreshRequestDto){
        return businessRefreshTokenRepository.findRefreshTokenByToken(tokenRefreshRequestDto.getRefreshToken())
                .map(refreshToken -> {
                    businessRefreshTokenService.deleteByBusiness(refreshToken.getBusiness());
                    return ResponseEntity.ok("Business logged out successfully");
                }).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refresh Token Not Found"));
    }


    @GetMapping("/test")
    public String tester(){
        return "Salam it is running";
    }
}
