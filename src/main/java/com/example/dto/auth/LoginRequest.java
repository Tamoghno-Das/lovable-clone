package com.example.dto.auth;

public record LoginRequest
        (
                String email,
                String password
        ) {
}
