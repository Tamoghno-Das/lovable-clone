package com.example.service;

import com.example.dto.project.ProjectRequest;
import com.example.dto.project.ProjectResponse;
import com.example.dto.project.ProjectSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<ProjectSummaryResponse> getUserProject();

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void softdelete (Long id);
}

