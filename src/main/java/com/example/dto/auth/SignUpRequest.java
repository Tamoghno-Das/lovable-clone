package com.example.dto.auth;

public record SignUpRequest
        (
                String name,
                String email,
                String password
        ){
}
