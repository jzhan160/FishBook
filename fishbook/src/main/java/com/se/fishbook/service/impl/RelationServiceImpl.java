package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.UserRelationshipMapper;
import com.se.fishbook.service.RelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("relationService")
public class RelationServiceImpl implements RelationService {
    @Resource
    private UserRelationshipMapper relationshipMapper;
}
