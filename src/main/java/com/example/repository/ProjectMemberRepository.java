package com.example.repository;

import com.example.entity.ProjectMember;
import com.example.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId>
{
    List<ProjectMember> findByIdProjectId(Long projectId);
}
