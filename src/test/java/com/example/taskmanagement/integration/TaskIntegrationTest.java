package com.example.taskmanagement.integration;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.TaskStatus;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
@Transactional
class TaskIntegrationTest {
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private TaskService taskService;
    
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setUserId(1L);
        task.setStatus(TaskStatus.PENDING);
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
    void getAllTasks_ShouldReturnUserTasks() {
        taskService.createTask(task);
        Task anotherTask = new Task();
        anotherTask.setTitle("Another Task");
        anotherTask.setDescription("Another Description");
        anotherTask.setUserId(1L);
        anotherTask.setStatus(TaskStatus.PENDING);
        taskService.createTask(anotherTask);
        
        List<Task> userTasks = taskService.getAllTasks(1L);
        assertEquals(2, userTasks.size());
    }

    @Test
    void deleteTask_ShouldRemoveTask() {
        Task createdTask = taskService.createTask(task);
        taskService.deleteTask(createdTask.getId());
        
        Optional<Task> deletedTask = taskService.getTaskById(createdTask.getId());
        assertFalse(deletedTask.isPresent());
    }
} 