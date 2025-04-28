package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Notification;
import com.example.taskmanagement.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        return notificationService.getNotificationById(id)
                .map(notification -> new ResponseEntity<>(notification, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(notificationService.getNotificationsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotificationsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(notificationService.getUnreadNotificationsByUserId(userId), HttpStatus.OK);
    }
} 