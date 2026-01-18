package com.example.security;

import com.example.enums.ProjectPermission;
import com.example.enums.ProjectRole;
import com.example.repository.ProjectMemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityExpressions {
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;
    public boolean canViewProject(Long projectId)
    {
            Long userId = authUtil.getCurrentUserId();
            return projectMemberRepository.findRoleByProjectIdAndUserId(projectId ,userId)
                    .map(role -> role.getPermissions().contains(ProjectPermission.VIEW)).orElse(false);
    }
    public boolean canEditProject(Long projectId)
    {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId ,userId)
                .map(role -> role.getPermissions().contains(ProjectPermission.EDIT)
                ).orElse(false);
    }
    public boolean canDeleteProject(Long projectId)
    {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
                .map(role -> role.getPermissions().contains(ProjectPermission.DELETE))
                .orElse(false);
    }
    public boolean canViewMembers(Long projectId)
    {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
                .map(role -> role.getPermissions().contains(ProjectPermission.VIEW_MEMBERS))
                .orElse(false);
    }
    public boolean canManageMembers(Long projectId)
    {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
                .map(role -> role.getPermissions().contains(ProjectPermission.MANAGE_MEMBERS))
                .orElse(false);
    }
}
