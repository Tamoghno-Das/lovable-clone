package com.example.service;

import com.example.dto.project.FileContentResponse;
import com.example.dto.project.FileNode;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);
    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
