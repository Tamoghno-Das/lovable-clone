package com.example.service;

import com.example.dto.auth.UserProfileResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserProfileResponse getProfile(Long userId);
}


