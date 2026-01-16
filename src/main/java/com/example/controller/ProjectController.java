package com.example.controller;

import com.example.dto.project.ProjectRequest;
import com.example.dto.project.ProjectResponse;
import com.example.dto.project.ProjectSummaryResponse;
import com.example.security.AuthUtil;
import com.example.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectController {

     ProjectService projectService;
     AuthUtil authUtil;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects()
    {
        return ResponseEntity.ok(projectService.getUserProject());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id)
    {
        return ResponseEntity.ok(projectService.getUserProjectById(id));

    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.createProject(request));


    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectRequest request)
    {
        return ResponseEntity.ok(projectService.updateProject(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectResponse> deleteProject(@PathVariable Long id)
    {
        projectService.softdelete(id);
        return ResponseEntity.noContent().build();
    }

}
