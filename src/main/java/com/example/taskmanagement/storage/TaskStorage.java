package com.example.taskmanagement.storage;

import com.example.taskmanagement.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskStorage {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    void deleteById(Long id);
    List<Task> findByUserId(Long userId);
} 