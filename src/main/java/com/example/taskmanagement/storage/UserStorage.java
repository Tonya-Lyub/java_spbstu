package com.example.taskmanagement.storage;

import com.example.taskmanagement.model.User;
import java.util.List;
import java.util.Optional;

public interface UserStorage {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
} 