package com.example.demo.service;

import com.example.demo.dto.AuthDtos.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.AuthDtos.RegisterRequest;
import lombok.extern.slf4j.Slf4j;



import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository users;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwt;

    public void register(RegisterRequest req) {
        if (users.existsByUsername(req.username())) {
            throw new IllegalArgumentException("Username taken");
        }
        var user = User.builder()
                .username(req.username())
                .password(encoder.encode(req.password()))
                .role("USER")
                //.createdAt(Instant.now())
                .build();
        users.save(user);
    }

    public TokenResponse login(LoginRequest req) {
        var user = users.findByUsername(req.username())
                .orElseThrow(() -> new IllegalArgumentException("Bad credentials"));
        if (!encoder.matches(req.password(), user.getPassword())) {
            throw new IllegalArgumentException("Bad credentials");
        }
        return new TokenResponse(jwt.generateToken(user.getUsername()));
    }
}

