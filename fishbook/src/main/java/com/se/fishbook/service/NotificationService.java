package com.se.fishbook.service;

import com.se.fishbook.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> showUnreadNotifications(Integer UserId);
    List<Notification> showReadNotifications(Integer UserId);
}
