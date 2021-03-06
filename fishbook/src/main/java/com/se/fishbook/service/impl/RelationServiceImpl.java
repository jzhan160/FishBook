package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.UserRelationshipMapper;
import com.se.fishbook.model.User;
import com.se.fishbook.model.UserRelationship;
import com.se.fishbook.model.UserRelationshipExample;
import com.se.fishbook.service.RelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/*Suppose the current user is A. So followers are people who are following A.
  In other words, A is the followee.
* And followees are people who are followed by A. Now A becomes the follower*/
@Service("relationService")
public class RelationServiceImpl implements RelationService {
    @Resource
    private UserRelationshipMapper relationshipMapper;

    @Override
    public List<Integer> allFollowees(User user) {

        List<UserRelationship> userRelationships = relationshipMapper.selectByFollowerId(user.getUserid());
        List<Integer> followeeId = new ArrayList<>();
        for(UserRelationship r : userRelationships){
            followeeId.add(r.getFolloweeid());
        }

        return followeeId;
    }
    /*return all followers*/
    @Override
    public List<Integer> allFollowers(User user){
        List<UserRelationship> userRelationships = relationshipMapper.selectByFolloweeId(user.getUserid());
        List<Integer> followerId = new ArrayList<>();
        for(UserRelationship r : userRelationships){
            followerId.add(r.getFollowerid());
        }

        return followerId;
    }
    /*remove the relationship*/
    public void deleteRelationship(Integer followerid,Integer followeeid){
        UserRelationshipExample re=new UserRelationshipExample();
        re.createCriteria().andFolloweeidEqualTo(followeeid).andFolloweridEqualTo(followerid);
        relationshipMapper.deleteByExample(re);
    }
    /*add relationship*/
    public void addRelationship(Integer followerid, Integer followeeid){
        UserRelationship ur = new UserRelationship();
        ur.setFolloweeid(followeeid);
        ur.setFollowerid(followerid);
        relationshipMapper.insert(ur);
        
    }
}
