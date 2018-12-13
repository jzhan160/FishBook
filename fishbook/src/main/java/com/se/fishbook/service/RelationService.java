package com.se.fishbook.service;

import com.se.fishbook.model.User;

import java.util.List;
//RelationService is used to operate relationship table for the business layer

public interface RelationService {
    //select followers of this user
    List<Integer> allFollowees(User user);
    List<Integer> allFollowers(User user);

    void deleteRelationship(Integer followerid,Integer followeeid);
    void addRelationship(Integer followerid, Integer followeeid);
}
