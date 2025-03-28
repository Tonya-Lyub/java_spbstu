package com.example.service;

import com.example.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);
    Optional<Task> getTaskById(Long id);
    List<Task> getAllTasks();
    void deleteTask(Long id);
    Task updateTask(Task task);
} 