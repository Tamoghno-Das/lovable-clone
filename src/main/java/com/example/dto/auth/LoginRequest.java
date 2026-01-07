package com.example.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record LoginRequest
        (
                @Email String email,
                @Size(min = 4, max = 50) String password
        ) {
}
