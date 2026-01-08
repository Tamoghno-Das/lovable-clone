package com.example.mapper;

import com.example.dto.auth.SignUpRequest;
import com.example.dto.auth.UserProfileResponse;
import com.example.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserProfileResponse toUserProfile(User user);

    User toEntity(SignUpRequest signUpRequest);
}

