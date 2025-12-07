package com.example.controller;

import com.example.dto.auth.AuthResponse;
import com.example.dto.auth.LoginRequest;
import com.example.dto.auth.SignUpRequest;
import com.example.dto.auth.UserProfileResponse;
import com.example.service.AuthService;
import com.example.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

         AuthService authService;
         UserService userService;

        @PostMapping("/signup")
        public ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest signUpRequest) {
                return ResponseEntity.ok(authService.signup(signUpRequest));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
            return ResponseEntity.ok(authService.login(loginRequest));
        }

        @GetMapping("/me")
        public ResponseEntity<UserProfileResponse> getProfile() {
            Long userId = 1L;
            return ResponseEntity.ok(userService.getProfile(userId));
        }

}
