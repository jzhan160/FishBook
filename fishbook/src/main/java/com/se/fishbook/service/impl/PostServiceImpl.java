package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.PostMapper;
import com.se.fishbook.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Resource
    private PostMapper postMapper;
}
