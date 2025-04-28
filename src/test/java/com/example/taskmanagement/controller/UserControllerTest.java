package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {
    private UserController userController;
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        when(userService.createUser(any(User.class))).thenReturn(user);
        ResponseEntity<User> response = userController.createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void getUser_ShouldReturnUser() {
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        ResponseEntity<User> response = userController.getUser(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void getUser_ShouldReturnNotFound() {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());
        ResponseEntity<User> response = userController.getUser(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        List<User> users = List.of(user);
        when(userService.getAllUsers()).thenReturn(users);
        ResponseEntity<List<User>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void deleteUser_ShouldReturnNoContent() {
        ResponseEntity<Void> response = userController.deleteUser(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUser(1L);
    }
} 