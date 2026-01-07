package com.example.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest
        (
               @Size(min = 1, max = 50) String name,
               @Email String email,
                @Size (min = 4) String password
        ){
}
