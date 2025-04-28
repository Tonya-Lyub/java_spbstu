package com.example.taskmanagement.service;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.storage.UserStorage;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User createUser(User user) {
        return userStorage.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userStorage.findById(id);
    }

    public List<User> getAllUsers() {
        return userStorage.findAll();
    }

    public void deleteUser(Long id) {
        userStorage.deleteById(id);
    }
} 