package com.se.fishbook.service;


import com.se.fishbook.model.Comment;

import java.util.List;
//CommentService is used to operate comment table for the business layer

public interface CommentService {
    List<Comment> selectCommentsByPostId(Integer postId);

    void addComment(Comment comment);

    Integer commentCountByPostId(Integer postId);
}
