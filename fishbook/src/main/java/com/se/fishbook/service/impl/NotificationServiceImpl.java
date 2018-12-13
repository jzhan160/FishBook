package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.NotificationMapper;
import com.se.fishbook.mapper.PostMapper;
import com.se.fishbook.model.Notification;
import com.se.fishbook.model.NotificationExample;
import com.se.fishbook.model.Post;
import com.se.fishbook.model.User;
import com.se.fishbook.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
    @Resource
    private NotificationMapper notificationMapper;

    @Resource
    private PostMapper postMapper;
    /*show unread notifications*/
    @Override
    public List<Notification> showUnreadNotifications(Integer UserId) {
        NotificationExample ne = new NotificationExample();
        ne.createCriteria().andReceiveridEqualTo(UserId).andViewedEqualTo(new Byte("0"));
        ne.setOrderByClause("NotificationId DESC");
        return notificationMapper.selectByExample(ne);
    }
    /*show notifications that are readed*/
    @Override
    public List<Notification> showReadNotifications(Integer UserId) {
        NotificationExample ne = new NotificationExample();
        ne.createCriteria().andReceiveridEqualTo(UserId).andViewedEqualTo(new Byte("1"));
        ne.setOrderByClause("NotificationId DESC");
        return notificationMapper.selectByExample(ne);
    }

    @Override
    public void newLikes(Integer postId, Integer userId) {
        Notification n = new Notification();
        n.setEvent("like " + postId);
        n.setTriggerid(userId);
        n.setViewed(new Byte("0"));
        n.setReceiverid(postMapper.selectByPrimaryKey(postId).getAuthorid());
        notificationMapper.insert(n);
    }
    /*new comments*/
    @Override
    public void newComments(Integer postId, Integer userId) {
        Notification n = new Notification();
        n.setEvent("comment " + postId);
        n.setTriggerid(userId);
        n.setReceiverid(postMapper.selectByPrimaryKey(postId).getAuthorid());
        n.setViewed(new Byte("0"));
        System.out.println(n.getEvent());
        notificationMapper.insert(n);
    }
    /*new follow*/
    @Override
    public void newFollow(Integer userId, Integer receiverId) {
        Notification n = new Notification();
        n.setEvent("follow");
        n.setTriggerid(userId);
        n.setReceiverid(receiverId);
        n.setViewed(new Byte("0"));
        System.out.println(n.getEvent());
        notificationMapper.insert(n);
    }
    /*new unfollow*/
    @Override
    public void newUnfollow(Integer userId, Integer receiverId) {
        Notification n = new Notification();
        n.setEvent("unfollow");
        n.setTriggerid(userId);
        n.setReceiverid(receiverId);
        n.setViewed(new Byte("0"));
        System.out.println(n.getEvent());
        notificationMapper.insert(n);
    }
}
