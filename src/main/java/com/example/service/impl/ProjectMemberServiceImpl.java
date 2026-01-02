package com.example.service.impl;

import com.example.dto.member.InviteMemberRequest;
import com.example.dto.member.MemberResponse;
import com.example.dto.member.UpdateMemberRoleRequest;
import com.example.entity.Project;
import com.example.entity.ProjectMemberId;
import com.example.mapper.ProjectMemberMapper;
import com.example.repository.ProjectMemberRepository;
import com.example.repository.ProjectRepository;
import com.example.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAllAccessibleProjectById(projectId,userId);

        List<MemberResponse> memberResponseList = new ArrayList<>();

        memberResponseList.add(projectMemberMapper.toProjectResponseFromOwner(project.getOwner()));

        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        return null;
    }

    @Override
    public MemberResponse deleteProjectMember(Long projectId, Long memberId) {
        return null;
    }


// WE CAN AVOID THE REPETITION THE CODE BY WRITING  INTERNAL FUNCTIONS

    public Project getAllAccessibleProjectById(Long projectId, Long userId)
    {
        return projectRepository.findAllAccessibleProjectById(projectId,userId).orElseThrow();



    }
}
