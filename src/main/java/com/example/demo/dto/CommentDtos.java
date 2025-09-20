package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentDtos {
    public record CreateCommentRequest(@NotBlank String text) {}
}
