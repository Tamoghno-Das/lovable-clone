package com.example.service.impl;

import com.example.entity.Project;
import com.example.entity.ProjectMember;
import com.example.entity.ProjectMemberId;
import com.example.entity.User;
import com.example.dto.project.ProjectRequest;
import com.example.dto.project.ProjectResponse;
import com.example.dto.project.ProjectSummaryResponse;
import com.example.enums.ProjectRole;
import com.example.error.ResourceNotFoundException;
import com.example.mapper.ProjectMapper;
import com.example.repository.ProjectMemberRepository;
import com.example.repository.ProjectRepository;
import com.example.repository.UserRepository;
import com.example.security.AuthUtil;
import com.example.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional

public class ProjectServiceImpl implements ProjectService {

     ProjectRepository projectRepository;

     UserRepository userRepository;

     ProjectMapper projectMapper;

     ProjectMemberRepository projectMemberRepository;

     AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request)
    {
        Long userId = authUtil.getCurrentUserId();
//        User owner = userRepository.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException("User",userId.toString())
//        );
        User owner = userRepository.getReferenceById(userId);

        Project project = Project
                .builder()
                .name(request.name())
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());

        ProjectMember projectMember = ProjectMember.builder()
                .projectMemberId(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);


        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProject() {

        Long  userId = authUtil.getCurrentUserId();
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toProjectSummaryResponseList(projects);
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")

    public ProjectResponse getUserProjectById(Long projectId)
    {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId,userId);
        return projectMapper.toProjectResponse(project);
    }



    @Override
    @PreAuthorize("@security.canEditProject(#id)")
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id,userId);
        project.setName(request.name());
        projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#id)")
    public void softdelete(Long id) {
        Long  userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id,userId);

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);

    }

    public Project getAccessibleProjectById(Long projectId, Long userId)
    {
        return projectRepository.findAllAccessibleProjectById(projectId,userId)
                .orElseThrow( () -> new ResourceNotFoundException("Project", projectId.toString()));
    }
}
