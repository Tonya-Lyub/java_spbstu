package com.example.taskmanagement.service;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.storage.UserStorage;
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
class UserServiceTest {
    @Mock
    private UserStorage userStorage;
    
    @InjectMocks
    private UserService userService;
    
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
    }

    @Test
    void createUser_ShouldCallStorageAndReturnUser() {
        when(userStorage.save(any(User.class))).thenReturn(user);
        
        User result = userService.createUser(user);
        
        verify(userStorage).save(user);
        assertEquals(user, result);
    }

    @Test
    void getUserById_ShouldReturnUserWhenExists() {
        when(userStorage.findById(1L)).thenReturn(Optional.of(user));
        
        Optional<User> result = userService.getUserById(1L);
        
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void getUserById_ShouldReturnEmptyWhenNotExists() {
        when(userStorage.findById(1L)).thenReturn(Optional.empty());
        
        Optional<User> result = userService.getUserById(1L);
        
        assertFalse(result.isPresent());
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        List<User> users = List.of(user);
        when(userStorage.findAll()).thenReturn(users);
        
        List<User> result = userService.getAllUsers();
        
        assertEquals(users, result);
    }

    @Test
    void deleteUser_ShouldCallStorage() {
        userService.deleteUser(1L);
        
        verify(userStorage).deleteById(1L);
    }
} 