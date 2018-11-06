package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.NotificationMapper;
import com.se.fishbook.model.Notification;
import com.se.fishbook.model.NotificationExample;
import com.se.fishbook.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
    @Resource
    private NotificationMapper notificationMapper;

    @Override
    public List<Notification> showUnreadNotifications(Integer UserId) {
        NotificationExample ne = new NotificationExample();
        ne.createCriteria().andReceiveridEqualTo(UserId).andViewedEqualTo(new Byte("0"));
        return notificationMapper.selectByExample(ne);
    }

    @Override
    public List<Notification> showReadNotifications(Integer UserId) {
        NotificationExample ne = new NotificationExample();
        ne.createCriteria().andReceiveridEqualTo(UserId).andViewedEqualTo(new Byte("1"));
        return notificationMapper.selectByExample(ne);
    }
}
