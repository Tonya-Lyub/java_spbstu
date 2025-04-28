package com.example.taskmanagement.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private LocalDateTime createdAt;
    private boolean read;
} 