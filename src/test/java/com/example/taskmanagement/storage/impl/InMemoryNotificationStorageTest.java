package com.example.taskmanagement.storage.impl;

import com.example.taskmanagement.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryNotificationStorageTest {
    private InMemoryNotificationStorage notificationStorage;
    private Notification notification;

    @BeforeEach
    void setUp() {
        notificationStorage = new InMemoryNotificationStorage();
        notification = new Notification();
        notification.setUserId(1L);
        notification.setMessage("Test Notification");
        notification.setRead(false);
    }

    @Test
    void save_ShouldAssignIdAndSaveNotification() {
        Notification savedNotification = notificationStorage.save(notification);
        assertNotNull(savedNotification.getId());
        assertEquals(notification.getMessage(), savedNotification.getMessage());
    }

    @Test
    void findById_ShouldReturnNotification() {
        Notification savedNotification = notificationStorage.save(notification);
        Optional<Notification> foundNotification = notificationStorage.findById(savedNotification.getId());
        assertTrue(foundNotification.isPresent());
        assertEquals(savedNotification.getId(), foundNotification.get().getId());
    }

    @Test
    void findAll_ShouldReturnAllNotifications() {
        notificationStorage.save(notification);
        List<Notification> notifications = notificationStorage.findAll();
        assertEquals(1, notifications.size());
    }

    @Test
    void findByUserId_ShouldReturnUserNotifications() {
        notificationStorage.save(notification);
        List<Notification> userNotifications = notificationStorage.findByUserId(1L);
        assertEquals(1, userNotifications.size());
        assertEquals(1L, userNotifications.get(0).getUserId());
    }

    @Test
    void findUnreadByUserId_ShouldReturnUnreadNotifications() {
        notificationStorage.save(notification);
        List<Notification> unreadNotifications = notificationStorage.findUnreadByUserId(1L);
        assertEquals(1, unreadNotifications.size());
        assertFalse(unreadNotifications.get(0).isRead());
    }
} 