package com.example.taskmanagement.storage.impl;

import com.example.taskmanagement.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserStorageTest {
    private InMemoryUserStorage userStorage;
    private User user;

    @BeforeEach
    void setUp() {
        userStorage = new InMemoryUserStorage();
        user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
    }

    @Test
    void save_ShouldAssignIdAndSaveUser() {
        User savedUser = userStorage.save(user);
        assertNotNull(savedUser.getId());
        assertEquals(user.getUsername(), savedUser.getUsername());
    }

    @Test
    void findById_ShouldReturnUser() {
        User savedUser = userStorage.save(user);
        Optional<User> foundUser = userStorage.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(savedUser.getId(), foundUser.get().getId());
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        userStorage.save(user);
        List<User> users = userStorage.findAll();
        assertEquals(1, users.size());
    }

    @Test
    void deleteById_ShouldRemoveUser() {
        User savedUser = userStorage.save(user);
        userStorage.deleteById(savedUser.getId());
        assertFalse(userStorage.findById(savedUser.getId()).isPresent());
    }
} 