package com.example.service;

import com.example.dto.auth.AuthResponse;
import com.example.dto.auth.LoginRequest;
import com.example.dto.auth.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse signup(SignUpRequest signUpRequest) ;
    AuthResponse login(LoginRequest loginRequest);
}
