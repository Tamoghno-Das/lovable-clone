package com.example.dto.member;

import com.example.enums.ProjectRole;

public record InviteMemberRequest
        (
                String email,
                ProjectRole role

        ) {
}
