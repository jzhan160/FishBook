package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.UserMapper;
import com.se.fishbook.model.User;
import com.se.fishbook.model.UserKey;
import com.se.fishbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;


    @Override
    public User selectById(UserKey key) {
        return userMapper.selectByPrimaryKey(key);
    }

    @Override
    public User selectByEmail(UserKey key) {
        return userMapper.selectByPrimaryKey(key);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }
}
