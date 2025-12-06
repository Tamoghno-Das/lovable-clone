package com.example.controller;

import com.example.dto.auth.AuthResponse;
import com.example.dto.auth.LoginRequest;
import com.example.dto.auth.SignUpRequest;
import com.example.dto.auth.UserProfileResponse;
import com.example.service.AuthService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

        private AuthService authService;
        private UserService userService;

        @PostMapping("/signup")
        public ResponseEntity<AuthResponse> signup(SignUpRequest signUpRequest) {
                return ResponseEntity.ok(authService.signup(signUpRequest));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
            return ResponseEntity.ok(authService.login(loginRequest));
        }

        @GetMapping("/me")
        public ResponseEntity<UserProfileResponse> getProfile() {
            Long userId = 1L;
            return ResponseEntity.ok(userService.getProfile(userId));
        }

}
