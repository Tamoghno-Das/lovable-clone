package com.example.dto.member;

import com.example.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse
        (
                Long id,
                String email,
                String name,
                String avatarUrl,
                ProjectRole role,
                Instant invitedAt

        ) {
}
