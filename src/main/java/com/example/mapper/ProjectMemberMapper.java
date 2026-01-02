package com.example.mapper;

import com.example.dto.member.MemberResponse;
import com.example.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    MemberResponse toProjectResponseFromOwner(User owner);

}
