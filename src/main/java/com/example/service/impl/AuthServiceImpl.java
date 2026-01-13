package com.example.service.impl;

import com.example.dto.auth.AuthResponse;
import com.example.dto.auth.LoginRequest;
import com.example.dto.auth.SignUpRequest;
import com.example.entity.User;
import com.example.error.BadRequestException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.security.AuthUtil;
import com.example.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignUpRequest signUpRequest) {

        userRepository.findByEmail(signUpRequest.email())
                .ifPresent
                        (user ->
                        {
                            throw new BadRequestException("User Already Exists with email : "+signUpRequest.email());
                        });

        User user = userMapper.toEntity(signUpRequest);
        user.setPasswordHash(passwordEncoder.encode(signUpRequest.password()));
        user = userRepository.save(user);

        // ACCESSING THE JWT TOKEN USING AUTHUTIL

        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token,userMapper.toUserProfile(user));
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token,userMapper.toUserProfile(user));
    }


}
