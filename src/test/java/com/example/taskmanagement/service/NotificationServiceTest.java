package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Notification;
import com.example.taskmanagement.storage.NotificationStorage;
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
class NotificationServiceTest {
    @Mock
    private NotificationStorage notificationStorage;
    
    @InjectMocks
    private NotificationService notificationService;
    
    private Notification notification;

    @BeforeEach
    void setUp() {
        notification = new Notification();
        notification.setId(1L);
        notification.setUserId(1L);
        notification.setMessage("Test Notification");
        notification.setRead(false);
    }

    @Test
    void createNotification_ShouldCallStorageAndReturnNotification() {
        when(notificationStorage.save(any(Notification.class))).thenReturn(notification);
        
        Notification result = notificationService.createNotification(notification);
        
        verify(notificationStorage).save(notification);
        assertEquals(notification, result);
    }

    @Test
    void getNotificationById_ShouldReturnNotificationWhenExists() {
        when(notificationStorage.findById(1L)).thenReturn(Optional.of(notification));
        
        Optional<Notification> result = notificationService.getNotificationById(1L);
        
        assertTrue(result.isPresent());
        assertEquals(notification, result.get());
    }

    @Test
    void getNotificationById_ShouldReturnEmptyWhenNotExists() {
        when(notificationStorage.findById(1L)).thenReturn(Optional.empty());
        
        Optional<Notification> result = notificationService.getNotificationById(1L);
        
        assertFalse(result.isPresent());
    }

    @Test
    void getAllNotifications_ShouldReturnAllNotifications() {
        List<Notification> notifications = List.of(notification);
        when(notificationStorage.findAll()).thenReturn(notifications);
        
        List<Notification> result = notificationService.getAllNotifications();
        
        assertEquals(notifications, result);
    }

    @Test
    void getNotificationsByUserId_ShouldReturnUserNotifications() {
        List<Notification> notifications = List.of(notification);
        when(notificationStorage.findByUserId(1L)).thenReturn(notifications);
        
        List<Notification> result = notificationService.getNotificationsByUserId(1L);
        
        assertEquals(notifications, result);
    }

    @Test
    void getUnreadNotificationsByUserId_ShouldReturnUnreadNotifications() {
        List<Notification> notifications = List.of(notification);
        when(notificationStorage.findUnreadByUserId(1L)).thenReturn(notifications);
        
        List<Notification> result = notificationService.getUnreadNotificationsByUserId(1L);
        
        assertEquals(notifications, result);
    }
} 