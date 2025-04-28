package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.storage.TaskStorage;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskStorage taskStorage;

    public TaskService(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    public Task createTask(Task task) {
        return taskStorage.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskStorage.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskStorage.findAll();
    }

    public void deleteTask(Long id) {
        taskStorage.deleteById(id);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskStorage.findByUserId(userId);
    }
} 