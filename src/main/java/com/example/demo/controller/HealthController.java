package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {

    @Value("${official.email}")
    private String email;

    @GetMapping("/health")
    public ApiResponse<Void> health() {
        return new ApiResponse<>(true, email, null);
    }
}