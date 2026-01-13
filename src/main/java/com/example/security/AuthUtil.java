package com.example.security;

import com.example.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
public class AuthUtil
{
        @Value("${app.security.jwt-secret}")
        private String jwtSecret;

        private SecretKey getSecretKey()
        {
                return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        }
        public String generateAccessToken(User user)
        {
                return  Jwts.builder()
                        .subject(user.getEmail())
                        .claim("userId",user.getId().toString())
                        .issuedAt(Date.from(Instant.now()))
                        .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                        .signWith(getSecretKey())
                        .compact();

        }
}
