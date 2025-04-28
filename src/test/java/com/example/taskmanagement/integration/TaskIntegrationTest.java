package com.example.taskmanagement.integration;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.storage.impl.InMemoryTaskStorage;
import com.example.taskmanagement.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class TaskIntegrationTest {
    private InMemoryTaskStorage taskStorage;
    private TaskService taskService;
    private Task task;

    @BeforeEach
    void setUp() {
        taskStorage = new InMemoryTaskStorage();
        taskService = new TaskService(taskStorage);
        task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setUserId(1L);
    }

    @Test
    void createAndRetrieveTask_ShouldWork() {
        Task createdTask = taskService.createTask(task);
        assertNotNull(createdTask.getId());
        
        Optional<Task> retrievedTask = taskService.getTaskById(createdTask.getId());
        assertTrue(retrievedTask.isPresent());
        assertEquals(createdTask.getId(), retrievedTask.get().getId());
        assertEquals(task.getTitle(), retrievedTask.get().getTitle());
    }

    @Test
    void getAllTasks_ShouldReturnAllCreatedTasks() {
        taskService.createTask(task);
        Task anotherTask = new Task();
        anotherTask.setTitle("Another Task");
        anotherTask.setUserId(1L);
        taskService.createTask(anotherTask);
        
        List<Task> allTasks = taskService.getAllTasks();
        assertEquals(2, allTasks.size());
    }

    @Test
    void deleteTask_ShouldRemoveTask() {
        Task createdTask = taskService.createTask(task);
        taskService.deleteTask(createdTask.getId());
        
        Optional<Task> deletedTask = taskService.getTaskById(createdTask.getId());
        assertFalse(deletedTask.isPresent());
    }

    @Test
    void getTasksByUserId_ShouldReturnOnlyUserTasks() {
        taskService.createTask(task);
        Task anotherTask = new Task();
        anotherTask.setTitle("Another Task");
        anotherTask.setUserId(2L);
        taskService.createTask(anotherTask);
        
        List<Task> userTasks = taskService.getTasksByUserId(1L);
        assertEquals(1, userTasks.size());
        assertEquals(1L, userTasks.get(0).getUserId());
    }
} 