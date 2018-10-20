package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.CommentMapper;
import com.se.fishbook.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
}
