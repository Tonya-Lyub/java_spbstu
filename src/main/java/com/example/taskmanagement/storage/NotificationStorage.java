package com.example.taskmanagement.storage;

import com.example.taskmanagement.model.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationStorage {
    Notification save(Notification notification);
    Optional<Notification> findById(Long id);
    List<Notification> findAll();
    List<Notification> findByUserId(Long userId);
    List<Notification> findUnreadByUserId(Long userId);
} 