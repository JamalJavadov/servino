package com.example.businessproject.service.securityservice;

import com.example.businessproject.model.entity.Business;
import com.example.businessproject.model.entity.BusinessRefreshToken;
import com.example.businessproject.model.entity.UserRefreshToken;
import com.example.businessproject.repository.BusinessRefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusinessRefreshTokenService {
    private final BusinessRefreshTokenRepository businessRefreshTokenRepository;
    private final long refreshTokenDurationMs = 7L * 24 * 60 * 60 * 1000;

    @Transactional
    public BusinessRefreshToken createRefreshToken(Business business){
        deleteByBusiness(business);
        BusinessRefreshToken refreshToken = BusinessRefreshToken.builder()
                .business(business)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
                .build();
        return businessRefreshTokenRepository.save(refreshToken);
    }

    public boolean isExpired(BusinessRefreshToken refreshToken){
        return refreshToken.getExpiryDate().isBefore(Instant.now());
    }

    @Transactional
    public void deleteByBusiness(Business business){
        businessRefreshTokenRepository.deleteByBusiness(business);
    }
}
