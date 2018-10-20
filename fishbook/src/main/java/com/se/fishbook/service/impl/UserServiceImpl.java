package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.UserMapper;
import com.se.fishbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
}
