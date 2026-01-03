package com.example.mapper;

import com.example.dto.member.MemberResponse;
import com.example.entity.ProjectMember;
import com.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userid", source = "id")
    @Mapping(target = "projectRole",constant = "OWNER")
    MemberResponse toProjectResponseFromOwner(User owner);


    // NESTED MAPPING USING MAPSTRUCT

    @Mapping(target = "userid", source = "user.id")
    @Mapping(target = "email",source = "user.email")
    @Mapping(target = "name",source = "user.name")
    MemberResponse toProjectResponseFromMember(ProjectMember member);
}
