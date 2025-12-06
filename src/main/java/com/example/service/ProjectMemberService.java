package com.example.service;

import com.example.dto.auth.entity.ProjectMember;
import com.example.dto.member.InviteMemberRequest;
import com.example.dto.member.MemberResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectMemberService {

    List<ProjectMember> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, InviteMemberRequest request);

    MemberResponse deleteProjectMember(Long projectId, Long memberId);
}
