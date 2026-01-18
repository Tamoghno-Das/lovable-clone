package com.example.repository;

import com.example.entity.ProjectMember;
import com.example.entity.ProjectMemberId;
import com.example.entity.User;
import com.example.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId>
{
    List<ProjectMember> findByProjectMemberId_ProjectId(Long projectId);

    @Query
            (
                    """
                    SELECT pm.projectRole FROM ProjectMember pm
                                        WHERE pm.projectMemberId.projectId = :projectId 
                                                            AND pm.projectMemberId.userId = :userId
                    """
            )
    Optional<ProjectRole> findRoleByProjectIdAndUserId(@Param("projectId") Long projectId,
                                                       @Param("userId") Long userId);

    Long user(User user);
}

