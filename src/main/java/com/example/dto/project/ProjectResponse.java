package com.example.dto.project;

import com.example.dto.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse
        (
                Long id,
                String name,
                Instant createdAt,
                Instant updatedAt,
                UserProfileResponse user

        ){
}
