package com.example.controller;

import com.example.dto.auth.entity.ProjectMember;
import com.example.dto.member.InviteMemberRequest;
import com.example.dto.member.MemberResponse;
import com.example.dto.member.UpdateMemberRoleRequest;
import com.example.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/members")
public class ProjectMemberController
{
    private final ProjectMemberService projectMemberService;

    @GetMapping("/{projectId}")
    public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId)
    {
        Long  userId = 1L;
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId,userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember
            (
                    @PathVariable Long projectId,
                    @RequestBody InviteMemberRequest request
            )
    {
        Long  userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectMemberService.inviteMember(projectId,request,userId));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMember
            (
                    @PathVariable Long projectId,
                    @PathVariable Long memberId,
                    @RequestBody UpdateMemberRoleRequest request
            )
    {
        Long  userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId,memberId,request));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberResponse> deleteMember
            (
                    @PathVariable Long projectId,
                    @PathVariable Long memberId

            )
    {
        Long  userId = 1L;
        return ResponseEntity.ok(projectMemberService.deleteProjectMember(projectId,memberId));
    }


}
