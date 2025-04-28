package com.example.taskmanagement.integration;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.storage.impl.InMemoryUserStorage;
import com.example.taskmanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class UserIntegrationTest {
    private InMemoryUserStorage userStorage;
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        userStorage = new InMemoryUserStorage();
        userService = new UserService(userStorage);
        user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
    }

    @Test
    void createAndRetrieveUser_ShouldWork() {
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser.getId());
        
        Optional<User> retrievedUser = userService.getUserById(createdUser.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals(createdUser.getId(), retrievedUser.get().getId());
        assertEquals(user.getUsername(), retrievedUser.get().getUsername());
    }

    @Test
    void getAllUsers_ShouldReturnAllCreatedUsers() {
        userService.createUser(user);
        User anotherUser = new User();
        anotherUser.setUsername("anotheruser");
        anotherUser.setEmail("another@example.com");
        userService.createUser(anotherUser);
        
        List<User> allUsers = userService.getAllUsers();
        assertEquals(2, allUsers.size());
    }

    @Test
    void deleteUser_ShouldRemoveUser() {
        User createdUser = userService.createUser(user);
        userService.deleteUser(createdUser.getId());
        
        Optional<User> deletedUser = userService.getUserById(createdUser.getId());
        assertFalse(deletedUser.isPresent());
    }
} 