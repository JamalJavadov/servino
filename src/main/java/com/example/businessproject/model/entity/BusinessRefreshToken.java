package com.example.businessproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessRefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String token;

    @OneToOne
    @JoinColumn(name = "business_id",referencedColumnName = "id")
    private Business business;

    @Column(nullable = false)
    private Instant expiryDate;
}
