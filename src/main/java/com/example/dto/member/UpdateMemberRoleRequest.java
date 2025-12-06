package com.example.dto.member;

import com.example.enums.ProjectRole;

public record UpdateMemberRoleRequest
        (
                ProjectRole role
        ) {
}
