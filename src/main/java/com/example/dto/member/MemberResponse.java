package com.example.dto.member;

import com.example.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse
        (
                Long userid,
                String email,
                String name,
                ProjectRole projectRole,
                Instant invitedAt

        ) {
}
