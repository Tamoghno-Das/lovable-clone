package com.example.service;

import com.example.dto.member.InviteMemberRequest;
import com.example.dto.member.MemberResponse;
import com.example.dto.member.UpdateMemberRoleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectMemberService {

    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void deleteProjectMember(Long projectId, Long memberId);
}
