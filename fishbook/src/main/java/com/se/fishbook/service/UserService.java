package com.se.fishbook.service;

import com.se.fishbook.model.User;
import com.se.fishbook.model.UserKey;

public interface UserService {
    User selectById(UserKey key);
    User selectByEmail(UserKey key);
}
