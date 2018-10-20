package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.NotificationMapper;
import com.se.fishbook.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
    @Resource
    private NotificationMapper notificationMapper;
}
