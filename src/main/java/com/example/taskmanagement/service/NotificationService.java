package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Notification;
import com.example.taskmanagement.storage.NotificationStorage;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationStorage notificationStorage;

    public NotificationService(NotificationStorage notificationStorage) {
        this.notificationStorage = notificationStorage;
    }

    public Notification createNotification(Notification notification) {
        return notificationStorage.save(notification);
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationStorage.findById(id);
    }

    public List<Notification> getAllNotifications() {
        return notificationStorage.findAll();
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationStorage.findByUserId(userId);
    }

    public List<Notification> getUnreadNotificationsByUserId(Long userId) {
        return notificationStorage.findUnreadByUserId(userId);
    }
} 