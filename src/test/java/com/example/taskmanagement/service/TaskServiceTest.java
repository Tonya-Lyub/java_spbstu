package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    
    @InjectMocks
    private TaskService taskService;
    
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setUserId(1L);
    }

    @Test
    void createTask_ShouldCallRepositoryAndReturnTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        
        Task result = taskService.createTask(task);
        
        verify(taskRepository).save(task);
        assertEquals(task, result);
    }

    @Test
    void getTaskById_ShouldReturnTaskWhenExists() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        
        Optional<Task> result = taskService.getTaskById(1L);
        
        assertTrue(result.isPresent());
        assertEquals(task, result.get());
    }

    @Test
    void getTaskById_ShouldReturnEmptyWhenNotExists() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        
        Optional<Task> result = taskService.getTaskById(1L);
        
        assertFalse(result.isPresent());
    }

    @Test
    void getAllTasks_ShouldReturnUserTasks() {
        List<Task> tasks = List.of(task);
        when(taskRepository.findByUserId(1L)).thenReturn(tasks);
        
        List<Task> result = taskService.getAllTasks(1L);
        
        assertEquals(tasks, result);
    }

    @Test
    void deleteTask_ShouldCallRepository() {
        taskService.deleteTask(1L);
        
        verify(taskRepository).deleteById(1L);
    }
} 