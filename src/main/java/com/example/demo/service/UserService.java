package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository users;
    private final BCryptPasswordEncoder passwordEncoder;

    public void createIfNotExists(String username, String rawPassword) {
        if (!users.existsByUsername(username)) {
            User u = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(rawPassword))
                    .role("USER")
                    .createdAt(OffsetDateTime.now())  // <-- задаем вручную
                    .build();
            users.save(u);
        }
    }
}
