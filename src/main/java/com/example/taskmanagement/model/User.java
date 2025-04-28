package com.example.taskmanagement.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
} 