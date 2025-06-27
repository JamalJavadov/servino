package com.example.businessproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BusinessProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessProjectApplication.class, args);
    }

}
