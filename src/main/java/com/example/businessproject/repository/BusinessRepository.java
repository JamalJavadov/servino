package com.example.businessproject.repository;

import com.example.businessproject.model.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Long> {
    Optional<Business> findBusinessesByContactMail(String contactMail);
}
