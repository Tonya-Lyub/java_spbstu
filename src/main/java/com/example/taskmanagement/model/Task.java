package com.example.taskmanagement.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

enum TaskStatus {
    TODO, IN_PROGRESS, DONE
} 