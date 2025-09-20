package com.example.demo.dto;

import com.example.demo.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskDtos {
    public record CreateTaskRequest(
            @NotBlank String title,
            String description
    ) {}

    public record UpdateTaskRequest(
            String title,
            String description,
            @NotNull TaskStatus status
    ) {}
}
