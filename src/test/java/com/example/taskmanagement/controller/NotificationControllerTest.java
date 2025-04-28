package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Notification;
import com.example.taskmanagement.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationControllerTest {
    private NotificationController notificationController;
    private NotificationService notificationService;
    private Notification notification;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        notificationController = new NotificationController(notificationService);
        notification = new Notification();
        notification.setId(1L);
        notification.setUserId(1L);
        notification.setMessage("Test Notification");
        notification.setRead(false);
    }

    @Test
    void createNotification_ShouldReturnCreatedNotification() {
        when(notificationService.createNotification(any(Notification.class))).thenReturn(notification);
        ResponseEntity<Notification> response = notificationController.createNotification(notification);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(notification, response.getBody());
    }

    @Test
    void getNotification_ShouldReturnNotification() {
        when(notificationService.getNotificationById(1L)).thenReturn(Optional.of(notification));
        ResponseEntity<Notification> response = notificationController.getNotification(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notification, response.getBody());
    }

    @Test
    void getNotification_ShouldReturnNotFound() {
        when(notificationService.getNotificationById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Notification> response = notificationController.getNotification(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllNotifications_ShouldReturnAllNotifications() {
        List<Notification> notifications = List.of(notification);
        when(notificationService.getAllNotifications()).thenReturn(notifications);
        ResponseEntity<List<Notification>> response = notificationController.getAllNotifications();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notifications, response.getBody());
    }

    @Test
    void getNotificationsByUserId_ShouldReturnUserNotifications() {
        List<Notification> notifications = List.of(notification);
        when(notificationService.getNotificationsByUserId(1L)).thenReturn(notifications);
        ResponseEntity<List<Notification>> response = notificationController.getNotificationsByUserId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notifications, response.getBody());
    }

    @Test
    void getUnreadNotificationsByUserId_ShouldReturnUnreadNotifications() {
        List<Notification> notifications = List.of(notification);
        when(notificationService.getUnreadNotificationsByUserId(1L)).thenReturn(notifications);
        ResponseEntity<List<Notification>> response = notificationController.getUnreadNotificationsByUserId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notifications, response.getBody());
    }
} 