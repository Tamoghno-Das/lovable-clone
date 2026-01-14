package com.example.service.impl;

import com.example.dto.member.InviteMemberRequest;
import com.example.dto.member.MemberResponse;
import com.example.dto.member.UpdateMemberRoleRequest;
import com.example.entity.Project;
import com.example.entity.ProjectMember;
import com.example.entity.ProjectMemberId;
import com.example.entity.User;
import com.example.mapper.ProjectMemberMapper;
import com.example.repository.ProjectMemberRepository;
import com.example.repository.ProjectRepository;
import com.example.repository.UserRepository;
import com.example.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAllAccessibleProjectById(projectId,userId);

        return projectMemberRepository
                .findByProjectMemberId_ProjectId(projectId)
                .stream().map(projectMemberMapper::toProjectResponseFromMember)
                .toList();

    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId)
    {
        Project project = getAllAccessibleProjectById(projectId,userId);

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();
        if(invitee.getId().equals(userId))
        {
            throw new RuntimeException("You Cannot invite  yourself");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());
        if(projectMemberRepository.existsById(projectMemberId))
        {
            throw new RuntimeException("Cannot invite once again");
        }

        ProjectMember projectMember = ProjectMember.builder()
                .projectMemberId(projectMemberId)
                .project(project)
                .user(invitee)
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectResponseFromMember(projectMember);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long  userId)
    {
        Project project =  getAllAccessibleProjectById(projectId,userId);
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectResponseFromMember(projectMember);
    }

    @Override
    public void deleteProjectMember(Long projectId, Long memberId, Long userId)
    {
        Project project =  getAllAccessibleProjectById(projectId,userId);
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId))
        {
            throw new RuntimeException("Member Not Found With The Project");
        }
        projectMemberRepository.deleteById(projectMemberId);
    }


// WE CAN AVOID THE REPETITION THE CODE BY WRITING  INTERNAL FUNCTIONS

    public Project getAllAccessibleProjectById(Long projectId, Long userId)
    {
        return projectRepository.findAllAccessibleProjectById(projectId,userId).orElseThrow();



    }
}
