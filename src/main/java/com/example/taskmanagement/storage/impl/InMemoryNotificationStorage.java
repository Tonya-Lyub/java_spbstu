package com.example.taskmanagement.storage.impl;

import com.example.taskmanagement.model.Notification;
import com.example.taskmanagement.storage.NotificationStorage;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class InMemoryNotificationStorage implements NotificationStorage {
    private final ConcurrentHashMap<Long, Notification> notifications = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Notification save(Notification notification) {
        if (notification.getId() == null) {
            notification.setId(idGenerator.getAndIncrement());
        }
        notifications.put(notification.getId(), notification);
        return notification;
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return Optional.ofNullable(notifications.get(id));
    }

    @Override
    public List<Notification> findAll() {
        return List.copyOf(notifications.values());
    }

    @Override
    public List<Notification> findByUserId(Long userId) {
        return notifications.values().stream()
                .filter(notification -> notification.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findUnreadByUserId(Long userId) {
        return notifications.values().stream()
                .filter(notification -> notification.getUserId().equals(userId) && !notification.isRead())
                .collect(Collectors.toList());
    }
} 