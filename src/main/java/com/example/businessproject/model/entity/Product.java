package com.example.businessproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String type;

    @OneToMany(mappedBy = "service",cascade = CascadeType.ALL)
    private List<Comment> comments;

    private Double rating;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    private Double price;

    private Integer totatUsedCount;

    @ManyToOne
    private Business business;

    @Column(name = "image_url")
    private String imageUrl;


}
