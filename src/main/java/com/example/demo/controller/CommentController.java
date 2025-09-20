package com.example.demo.controller;

import com.example.demo.dto.CommentDtos.CreateCommentRequest;
import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@RestController @RequestMapping("/api/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @GetMapping
    public List<Comment> list(@PathVariable Long taskId) {
        return service.list(taskId);
    }

    @PostMapping
    public Comment add(@PathVariable Long taskId,
                       @Valid @RequestBody CreateCommentRequest req,
                       Authentication auth) {
        return service.add(taskId, req, auth);
    }
}
