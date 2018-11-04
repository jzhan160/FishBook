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

@Service("relationService")
public class RelationServiceImpl implements RelationService {
    @Resource
    private UserRelationshipMapper relationshipMapper;

    @Override
    public List<Integer> allFollowees(User user) {
        List<UserRelationship> userRelationships = relationshipMapper.selectByFolloweeId(user.getUserid());
        List<Integer> followeeId = new ArrayList<>();
        for(UserRelationship r : userRelationships){
            followeeId.add(r.getFolloweeid());
        }

        return followeeId;
    }
}
