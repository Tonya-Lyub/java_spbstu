package com.example.service;

import com.example.model.Notification;
import java.util.List;

public interface NotificationService {
    List<Notification> getUnreadNotifications(Long userId);
    List<Notification> getAllNotifications(Long userId);
} 