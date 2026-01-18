package com.example.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectPermission {
    VIEW("project:view"),   // IT SHOULD BE DEFINED LIKE RESOURCE:ACTION FOR BETTER APPROACH
    EDIT("project:edit"),
    DELETE("project:delete"),
    MANAGE_MEMBERS("project:manage_members"),
    VIEW_MEMBERS("project:view_members"),;

    private final String value;
}
