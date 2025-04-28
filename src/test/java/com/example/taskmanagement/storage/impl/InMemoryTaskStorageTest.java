package com.example.taskmanagement.storage.impl;

import com.example.taskmanagement.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskStorageTest {
    private InMemoryTaskStorage taskStorage;
    private Task task;

    @BeforeEach
    void setUp() {
        taskStorage = new InMemoryTaskStorage();
        task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setUserId(1L);
    }

    @Test
    void save_ShouldAssignIdAndSaveTask() {
        Task savedTask = taskStorage.save(task);
        assertNotNull(savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
    }

    @Test
    void findById_ShouldReturnTask() {
        Task savedTask = taskStorage.save(task);
        Optional<Task> foundTask = taskStorage.findById(savedTask.getId());
        assertTrue(foundTask.isPresent());
        assertEquals(savedTask.getId(), foundTask.get().getId());
    }

    @Test
    void findAll_ShouldReturnAllTasks() {
        taskStorage.save(task);
        List<Task> tasks = taskStorage.findAll();
        assertEquals(1, tasks.size());
    }

    @Test
    void deleteById_ShouldRemoveTask() {
        Task savedTask = taskStorage.save(task);
        taskStorage.deleteById(savedTask.getId());
        assertFalse(taskStorage.findById(savedTask.getId()).isPresent());
    }

    @Test
    void findByUserId_ShouldReturnUserTasks() {
        taskStorage.save(task);
        List<Task> userTasks = taskStorage.findByUserId(1L);
        assertEquals(1, userTasks.size());
        assertEquals(1L, userTasks.get(0).getUserId());
    }
} 