package com.example.dto.member;

import com.example.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest
        (
                 @NotNull ProjectRole role
        ) {
}
