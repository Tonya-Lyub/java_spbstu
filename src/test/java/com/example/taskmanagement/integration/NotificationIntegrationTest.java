package com.example.taskmanagement.integration;

import com.example.taskmanagement.model.Notification;
import com.example.taskmanagement.storage.impl.InMemoryNotificationStorage;
import com.example.taskmanagement.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class NotificationIntegrationTest {
    private InMemoryNotificationStorage notificationStorage;
    private NotificationService notificationService;
    private Notification notification;

    @BeforeEach
    void setUp() {
        notificationStorage = new InMemoryNotificationStorage();
        notificationService = new NotificationService(notificationStorage);
        notification = new Notification();
        notification.setUserId(1L);
        notification.setMessage("Test Notification");
        notification.setRead(false);
    }

    @Test
    void createAndRetrieveNotification_ShouldWork() {
        Notification createdNotification = notificationService.createNotification(notification);
        assertNotNull(createdNotification.getId());
        
        Optional<Notification> retrievedNotification = notificationService.getNotificationById(createdNotification.getId());
        assertTrue(retrievedNotification.isPresent());
        assertEquals(createdNotification.getId(), retrievedNotification.get().getId());
        assertEquals(notification.getMessage(), retrievedNotification.get().getMessage());
    }

    @Test
    void getAllNotifications_ShouldReturnAllCreatedNotifications() {
        notificationService.createNotification(notification);
        Notification anotherNotification = new Notification();
        anotherNotification.setUserId(1L);
        anotherNotification.setMessage("Another Notification");
        anotherNotification.setRead(true);
        notificationService.createNotification(anotherNotification);
        
        List<Notification> allNotifications = notificationService.getAllNotifications();
        assertEquals(2, allNotifications.size());
    }

    @Test
    void getNotificationsByUserId_ShouldReturnOnlyUserNotifications() {
        notificationService.createNotification(notification);
        Notification anotherNotification = new Notification();
        anotherNotification.setUserId(2L);
        anotherNotification.setMessage("Another Notification");
        notificationService.createNotification(anotherNotification);
        
        List<Notification> userNotifications = notificationService.getNotificationsByUserId(1L);
        assertEquals(1, userNotifications.size());
        assertEquals(1L, userNotifications.get(0).getUserId());
    }

    @Test
    void getUnreadNotificationsByUserId_ShouldReturnOnlyUnreadNotifications() {
        notificationService.createNotification(notification);
        Notification readNotification = new Notification();
        readNotification.setUserId(1L);
        readNotification.setMessage("Read Notification");
        readNotification.setRead(true);
        notificationService.createNotification(readNotification);
        
        List<Notification> unreadNotifications = notificationService.getUnreadNotificationsByUserId(1L);
        assertEquals(1, unreadNotifications.size());
        assertFalse(unreadNotifications.get(0).isRead());
    }
} 