package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.PostMapper;
import com.se.fishbook.model.Post;
import com.se.fishbook.model.PostExample;
import com.se.fishbook.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Resource
    private PostMapper postMapper;

    @Override
    public List<Post> postsByUserIds(List<Integer> ids) {
        PostExample post = new PostExample();
        post.createCriteria().andAuthoridIn(ids);
        post.setOrderByClause("CreateTime");
        return postMapper.selectByExample(post);
    }

    @Override
    public List<Post> postByUserId(Integer id) {
        PostExample post = new PostExample();
        post.createCriteria().andAuthoridEqualTo(id);
        post.setOrderByClause("CreateTime");
        return postMapper.selectByExample(post);
    }
}
