package com.se.fishbook.mapper;

import com.se.fishbook.model.UserRelationship;
import com.se.fishbook.model.UserRelationshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
//operations on the relationship table

public interface UserRelationshipMapper {
    int countByExample(UserRelationshipExample example);

    int deleteByExample(UserRelationshipExample example);

    int deleteByPrimaryKey(Integer relationshipid);

    int insert(UserRelationship record);

    int insertSelective(UserRelationship record);

    List<UserRelationship> selectByExample(UserRelationshipExample example);

    List<UserRelationship> selectByFollowerId(Integer followerId);

    List<UserRelationship> selectByFolloweeId(Integer followeeId);

    UserRelationship selectByPrimaryKey(Integer relationshipid);

    int updateByExampleSelective(@Param("record") UserRelationship record, @Param("example") UserRelationshipExample example);

    int updateByExample(@Param("record") UserRelationship record, @Param("example") UserRelationshipExample example);

    int updateByPrimaryKeySelective(UserRelationship record);

    int updateByPrimaryKey(UserRelationship record);


}