package com.example.service;

import com.example.dto.auth.entity.Project;
import com.example.dto.project.ProjectRequest;
import com.example.dto.project.ProjectResponse;
import com.example.dto.project.ProjectSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public interface ProjectService {
    List<ProjectSummaryResponse> getUserProject(Long userId);

    ProjectResponse getUserProjectById(Long id,Long userId);

    ProjectResponse createProject(ProjectRequest request, Long userId);

    ProjectResponse updateProject(Long id, ProjectRequest request, Long userId);

    ProjectResponse deleteProject(Long id, Long userId);
    void softdelete(Long id, Long userId);
}

