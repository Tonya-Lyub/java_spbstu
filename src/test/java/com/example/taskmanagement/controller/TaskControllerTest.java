package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {
    private TaskController taskController;
    private TaskService taskService;
    private Task task;

    @BeforeEach
    void setUp() {
        taskService = mock(TaskService.class);
        taskController = new TaskController(taskService);
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setUserId(1L);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() {
        when(taskService.createTask(any(Task.class))).thenReturn(task);
        ResponseEntity<Task> response = taskController.createTask(task);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void getTask_ShouldReturnTask() {
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(task));
        ResponseEntity<Task> response = taskController.getTask(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void getTask_ShouldReturnNotFound() {
        when(taskService.getTaskById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Task> response = taskController.getTask(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllTasks_ShouldReturnAllTasks() {
        List<Task> tasks = List.of(task);
        when(taskService.getAllTasks()).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getAllTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void deleteTask_ShouldReturnNoContent() {
        ResponseEntity<Void> response = taskController.deleteTask(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService).deleteTask(1L);
    }

    @Test
    void getTasksByUserId_ShouldReturnUserTasks() {
        List<Task> tasks = List.of(task);
        when(taskService.getTasksByUserId(1L)).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getTasksByUserId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }
} 