package com.example.businessproject.repository;

import com.example.businessproject.model.entity.Business;
import com.example.businessproject.model.entity.BusinessRefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BusinessRefreshTokenRepository extends JpaRepository<BusinessRefreshToken,Long> {
    Optional<BusinessRefreshToken> findRefreshTokenByToken(String token);

    @Modifying
    @Transactional
    @Query("DELETE FROM BusinessRefreshToken r WHERE r.business = :business")
    void deleteByBusiness(@Param("business") Business business);
}
