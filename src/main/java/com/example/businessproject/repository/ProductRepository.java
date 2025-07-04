package com.example.businessproject.repository;

import com.example.businessproject.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findProductById(long id);

    List<Product> findAllByBusiness_ContactMail(String businessContactMail);
}
