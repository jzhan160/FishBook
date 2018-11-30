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

    @Override
    public List<Notification> showUnreadNotifications(Integer UserId) {
        NotificationExample ne = new NotificationExample();
        ne.createCriteria().andReceiveridEqualTo(UserId).andViewedEqualTo(new Byte("0"));
        ne.setOrderByClause("NotificationId DESC");
        return notificationMapper.selectByExample(ne);
    }

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
        n.setEvent("Like");
        n.setTriggerid(userId);
        n.setReceiverid(postMapper.selectByPrimaryKey(postId).getAuthorid());
        notificationMapper.insert(n);
    }

    @Override
    public void newComments(Integer postId, Integer userId) {
        Notification n = new Notification();
        n.setEvent("Comment");
        n.setTriggerid(userId);
        n.setReceiverid(postMapper.selectByPrimaryKey(postId).getAuthorid());
        notificationMapper.insert(n);
    }
}
