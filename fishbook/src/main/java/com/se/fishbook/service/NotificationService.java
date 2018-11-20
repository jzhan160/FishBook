package com.se.fishbook.service;

import com.se.fishbook.model.Notification;
import com.se.fishbook.model.Post;
import com.se.fishbook.model.User;

import java.util.List;

public interface NotificationService {
    List<Notification> showUnreadNotifications(Integer UserId);
    List<Notification> showReadNotifications(Integer UserId);
    void newLikes(Integer postId, Integer userId);
    void newComments (Integer postId, Integer userId);
}
