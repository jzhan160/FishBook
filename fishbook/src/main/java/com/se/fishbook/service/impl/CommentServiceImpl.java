package com.se.fishbook.service.impl;

import com.se.fishbook.mapper.CommentMapper;
import com.se.fishbook.model.Comment;
import com.se.fishbook.model.CommentExample;
import com.se.fishbook.model.Post;
import com.se.fishbook.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectCommentsByPostId(Integer postId) {
        CommentExample ce = new CommentExample();
        ce.createCriteria().andPostidEqualTo(postId);
        ce.setOrderByClause("CreateTime DESC");
        return commentMapper.selectByExample(ce);
    }

    @Override
    public Integer commentCountByPostId(Integer postId) {
        CommentExample ce = new CommentExample();
        ce.createCriteria().andPostidEqualTo(postId);
        return commentMapper.selectByExample(ce).size();
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }
}
