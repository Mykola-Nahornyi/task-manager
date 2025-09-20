package com.example.demo.service;

import com.example.demo.dto.CommentDtos.CreateCommentRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;



import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository comments;
    private final TaskRepository tasks;
    private final UserRepository users;

    private User currentUser(Authentication auth) {
        return users.findByUsername(auth.getName()).orElseThrow();
    }

    public List<Comment> list(Long taskId) {
        Task t = tasks.findById(taskId).orElseThrow();
        return comments.findByTask(t);
    }

    @Transactional
    public Comment add(Long taskId, CreateCommentRequest req, Authentication auth) {
        Task t = tasks.findById(taskId).orElseThrow();
        User me = currentUser(auth);
        var c = Comment.builder()
                .task(t)
                .author(me)
                .text(req.text())
                .createdAt(OffsetDateTime.now()) // присваиваем поле createdAt
                .build();
        return comments.save(c);
    }
}
