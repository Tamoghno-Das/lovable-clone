package com.example.service.impl;

import com.example.entity.Project;
import com.example.entity.User;
import com.example.dto.project.ProjectRequest;
import com.example.dto.project.ProjectResponse;
import com.example.dto.project.ProjectSummaryResponse;
import com.example.mapper.ProjectMapper;
import com.example.repository.ProjectRepository;
import com.example.repository.UserRepository;
import com.example.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional

public class ProjectServiceImpl implements ProjectService {

    final ProjectRepository projectRepository;

    final UserRepository userRepository;

    final ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId)
    {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder().name(request.name())
                .owner(owner).isPublic(false).build();

        project = projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProject(Long userId) {
//
//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(project -> projectMapper.toProjectSummaryResponse(project))
//                .toList();

        // Better Version of Conversion...

        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toProjectSummaryResponseList(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId)
    {
        Project project = projectRepository.findAllAccessibleProjectById(id,userId).orElseThrow();
        return projectMapper.toProjectResponse(project);
    }



    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = projectRepository.findAllAccessibleProjectById(id,userId).orElseThrow();

        if(!project.getOwner().getId().equals(userId))
        {
            throw new RuntimeException("You are not allowed to delete");

        }
        project.setName(request.name());
        projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse deleteProject(Long id, Long userId) {
        return null;
    }

    @Override
    public void softdelete(Long id, Long userId) {
        Project project =  projectRepository.findAllAccessibleProjectById(id,userId).orElseThrow();
        if(!project.getOwner().getId().equals(userId))
        {
            throw new RuntimeException("You are not allowed to delete");

        }
        else {
            project.setDeletedAt(Instant.now());
            projectRepository.save(project);
        }

    }
}
