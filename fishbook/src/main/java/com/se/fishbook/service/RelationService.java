package com.se.fishbook.service;

import com.se.fishbook.model.User;

import java.util.List;

public interface RelationService {
    //select followers of this user
    List<Integer> allFollowees(User user);
    List<Integer> allFollowers(User user);
}
