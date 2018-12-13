package com.se.fishbook.mapper;

import com.se.fishbook.model.User;
import com.se.fishbook.model.UserExample;
import com.se.fishbook.model.UserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
//operations on the user table

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(UserKey key);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(UserKey key);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}